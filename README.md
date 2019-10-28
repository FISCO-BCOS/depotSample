# DepotSample服务说明文档


## 一、背景介绍

- FISCO-BCOS中交易（Tranction）和交易结果（TransactionReceipt ）都是保存在区块（Block）中；如果以Block为单位链接起来，则构成更大粒度的BlockChain；区块数据最终以[k,v]键值对的存储形式存放在底层数据库LevelDB中。key是blockhash，value是区块数据的rlp编码。此种存储形式不支持传统的结构化查询，用户不易查看，不便使用。 
- FISCO-BCOS DepotSample服务是基于FISCO-BCOS区块链开发的应用样例，提供了一种将 FISCO-BCOS区块链上交易数据导出到DB的方案。联盟链用户可以通过此方案，从可信节点把交易执行的结果导出到传统DB，便于系统查询，同时也便于传统运营系统的接入。

## 二、原理介绍

- FISCO-BCOS DepotSample 其原理是在业务合约中对所需的业务数据进行事件（Events）埋点，当链上不断有相应交易发生时，就会不断触发合约中埋点的事件，FISCO-BCOS将埋点的业务数据一起打包进区块的TransactionReceipt中。FISCO-BCOS Depot服务通过监控区块链上区块的变化，不断查询新产生的区块，分析新区块的交易回执信息（TransactionReceipt），查询区块中已经触发的事件，解析事件中的埋点数据同步到本地的DB。

### 2.1、相关名词解释
- Events:是抽象出来的区块链日志，事件监听协议。日志实体包含合约的地址，一系列的最多可以达到4个的Topic，和任意长度的二进制数据内容。Events依赖ABI函数来解释，日志实体被当成为一个自定义数据结构。事件有一个给定的事件名称，一系列的事件参数，我们将他们分为两个类别：需要索引的和不需要索引的。需要索引的，可以最多允许有三个，包含使用Keccak hash算法哈希过的事件签名，来组成现在日志实体的Topic。那些不需要索引的组成了Events的字节数组。详细协议说明可查看[Ethereum-Contract-ABI#events](https://solidity.readthedocs.io/en/v0.5.3/abi-spec.html#events)。
- Transaction Receipts：交易回执记录着交易的执行结果，保存在区块信息中。交易回执结构中包括了区块哈希，区块高度，交易哈希，交易索引，from，to，gas，合约地址，logs。其中的logs就是上面说到的Events。


## 三、主要流程

- FISCO-BCOS DepotSample服务以存证业务（evidenceSample）为例，对存证上链业务的Events进行解析，方便开发者对Depot服务有一个直观的认识。
- 本服务存证示例依赖于金链盟开源的区块链存证案例工具包，需下载[evidence_toolkit](https://github.com/FISCO-BCOS/evidenceSample/releases/tag/v1.0.0)，想了解完整存证案例请参看[evidenceSample](https://github.com/FISCO-BCOS/evidenceSample/releases/tag/v1.0.0)。

### 3.1、DepotSample的主要工作流程
1. 搭建区块链环境。
2. 使用evidence_toolkit发送存证业务数据上链（业务触发Events）。
3. 交易成功后生成区块，区块高度增加，事件埋入区块中。
4. Depot服务获取和解析交易回执Transaction Receipts，将解析出来的数据存入本地DB中。<br>
![DepotSample流程](/file/depot.png)

### 3.2、业务使用模板的修改步骤

1. 开发者通过在业务合约中进行事件埋点。
2. 生成相应的Events Java类。
3. 设计Events与DB数据表结构的映射关系。
4. 通过mybatis-generator-gui生成Mybatis的java POJO文件及数据库Mapping文件，用于把Events写入到DB。
5. 重写DepotFacadeImpl的handleReceipt方法。
6. 运行服务将交易回执中的埋点信息解析并存入相应的表中。


## 四、服务环境搭建

1. 搭建FISCO-BCOS区块链环境（参考FISCO-BCOS使用文档），操作系统为CentOS7.2。在此使用单个区块链节点来模拟区块链环境。<br>
  一键安装说明URL：https://github.com/FISCO-BCOS/FISCO-BCOS/tree/master/sample
2. 安装配置Java环境（jdk1.8），gradle环境（gradle-2.1或以上版本）。详情请查看官网：<br>
    jdk官网URL：http://www.oracle.com/technetwork/java/javase/downloads/index.html<br>
    gradle官网URL：http://www.gradle.org/downloads
3. Windows下将Depot服务源码导入Eclipse（本说明文档以Eclipse为例）。DepotSample工程为gradle工程，导入时通过“Gradle Project”导入。<br>
  DepotSample案例下载URL：https://github.com/FISCO-BCOS/evidenceSample/releases/tag/v1.0.0
4. 在DepotSample工程的/depotSample/src/main/resources/applicationContext.xml文件配置区块链节点信息。

    ```
    <bean id="channelService" class="cn.webank.channel.client.Service">
    		<property name="orgID" value="user" /><!-- 配置角色名称 -->
    		<property name="allChannelConnections">
    			<map>
    				<entry key="user"><!-- 配置本角色的区块链节点列表-->
    					<bean class="org.bcos.channel.handler.ChannelConnections">
    						<property name="connectionsStr">
    							<list>
    						        <value>node@127.0.0.1:8541</value><!-- 格式：节点名@节点所在服务器IP地址:channelPort端口（节点名可以为任意名称）-->
    							</list>
    						</property>
    					</bean>
    				</entry>
    			</map>
    		</property>
    	</bean>
    ```

5. 在DepotSample工程的/depot/src/main/resources/db.properties文件配置数据库信息。

    ```
    db.ip=127.0.0.1
    db.port=3306
    db.user=root
    db.password=123456
    db.database=depot
    ```

## 五、DepotSample案例使用详细步骤

- DepotSample服务以存证业务为例，对创建证据（newEvidence）业务进行埋点，获取证据ID、证据hash、证据签名signData等信息。
- 开发者如果想直接使用存证业务来了解DepotSample服务，从5.7节进行即可。如果需要对自己的业务合约进行埋点，获取自己的业务数据，请从5.1节开始。

### 5.1、创建业务合约并对需要记录的业务数据进行埋点

- 智能合约语法及细节请参考[solidity官方文档](https://solidity.readthedocs.io/en/develop/solidity-in-depth.html)。
- 这里使用evidence_toolkit工具包contracts目录下的合约Evidence.sol进行说明。

    ```
	合约路径：/evidence_toolkit/contracts/Evidence.sol
	
	以下粘贴部分代码，详细代码参看文件。在Evidence.sol合约中创建埋点事件newSignaturesEvent，对证据ID（id）、证据hash（evi）、证据签名信息（v、r、s）、工厂合约地址（addr）进行埋点，然后在Evidence方法中调用newSignaturesEvent。
	当发送创建证据（newEvidence）业务交易时，会执行Evidence方法，从而将埋点信息埋入区块中。
	
	event newSignaturesEvent(string evi, string info, string id, uint8 v, bytes32 r, bytes32 s,address addr);
    function Evidence(string evi, string info, string id, uint8 v, bytes32 r, bytes32 s, address addr, address sender) public {
       signersAddr = addr;
       if(CallVerify(sender))
       {
           evidence = evi;
           evidenceInfo = info;
           evidenceId = id;
           _v.push(v);
           _r.push(r);
           _s.push(s);
           signers.push(sender);
           newSignaturesEvent(evi,info,id,v,r,s,addr);
       }
       else
       {
           errorNewSignaturesEvent(evi,info,id,v,r,s,addr);
       }
    }
    ```

### 5.2、编译合约生成Java解析文件

- 开发者可以按照以下步骤使用对自己的合约进行编译。

1. 安装solidity合约编译器。这里使用[fisco-solc](https://github.com/FISCO-BCOS/FISCO-BCOS/fisco-solc)，将下载下来的fisco-solc拷贝到Linux的/usr/bin目录下，执行命令chmod +x fisco-solc，即可安装完成。
2. 将下载的存证案例工具包evidence_toolkit上传到Linux。
3. 将自己创建的业务合约复制到evidence_toolkit/contracts目录下。（编译前需要在此文件夹下需要先删掉其他无关合约）。
4. 在evidence_toolkit/conf/applicationContext.xml文件中配置区块链节点信息（参考4.3）。
5. 进入evidence_toolkit/bin目录，执行以下命令编译合约。

    ```
	./compile.sh org.bcos.depot.contract
    ```

6. 编译后会在/evidence_toolkit/output/org/bcos/depot/contract/目录生成合约的对应java解析文件。

### 5.3、根据需要记录的数据创建业务数据表

- 开发者根据自己埋点的数据创建业务数据表。
- 这里以存证业务为例，创建证据信息表t_evidence_info，字段包括自增主键（id），交易发生所在块高（block_height），埋点事件名（event）、证据ID（evidence_id）、证据hash（evidence_hash）、证据签名信息（sign_data）、工厂合约地址（factory_address）。

    ```
    CREATE TABLE t_evidence_info (
      id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
      block_height int NOT NULL COMMENT '交易所在块高',
      event varchar(128) NOT NULL COMMENT 'event',
      evidence_id varchar(128) NOT NULL COMMENT '证据ID',
      evidence_hash varchar(256) NOT NULL COMMENT '证据hash',
      sign_data varchar(256) DEFAULT '' COMMENT '签名数据',
      factory_address varchar(256) DEFAULT '' COMMENT '工厂合约地址',
      PRIMARY KEY (id)
    ) COMMENT='证据信息表' ENGINE=InnoDB DEFAULT CHARSET=utf8;
    ```

### 5.4、生成Java实体类、数据库接口Dao和Mapper文件

- DepotSample服务使用Spring+Mybatis架构。可以使用mybatis-generator-gui工具根据数据表生成对应的Java实体类、数据库接口Dao和Mapper文件。mybatis-generator-gui工具使用说明可自行查询，这里提供参考（URL：https://github.com/zouzg/mybatis-generator-gui ）。<br>
- 生成配置参考<br>
![生成配置参考](/file/mapper.png)

### 5.5、文件目录

- 将5.2.6、5.3、5.4生成的文件放DepotSample工程以下目录。开发者根据自己的业务新建的文件也需要放在以下目录：

    ```
	合约编译的Java文件：/depot/src/main/java/org/bcos/depot/contract/Evidence.java
	业务数据表：/depot/script/db/t_evidence_info.sql
	数据表对应Java实体类：/depot/src/main/java/org/bcos/depot/entity/EvidenceInfo.java
	                    /depot/src/main/java/org/bcos/depot/entity/EvidenceInfoExample.java
	数据表对应接口Dao：/depot/src/main/java/org/bcos/depot/mapper/EvidenceInfoDao.java
	数据表对应Mapper文件：/depot/src/main/resources/mappers/EvidenceInfoDao.xml
    ```

### 5.6、业务数据解析

- 开发者对自己的埋点业务数据进行解析时，需要重写DepotSample服务接口DepotFacade中的handleReceipt方法，将交易回执中的埋点数据解析存入到业务表中。
- 这里以存证业务为例，通过get埋点事件名"getNewSignaturesEventEvents"从交易回执"receipt"中获取证据id、证据hash、证据签名信息、工厂合约地址，存入到t_evidence_info表中。

    ```
	接口实现类：/depot/src/main/java/org/bcos/depot/facade/impl/DepotFacadeImpl.java
	实现类说明：①接口实现类参数为交易回执信息（receipt）、初始化web3j、当前交易所在块高（blockNumber）
              ②接口实现类返回类型为boolean，当解析存储成功返回true，失败则返回false
              ③实现类中首先通过合约对应Java类的load()方法实例化一个对象，由该对象通过埋点的事件名从交易回执信息（receipt）中获取埋点信息，然后解析存入数据库

	@Override
	public boolean handleReceipt(TransactionReceipt receipt, Web3j web3j, BigInteger blockNumber) {
		try {
			ECKeyPair ecKeyPair = Keys.createEcKeyPair();
			evidence = Evidence.load("", web3j, Credentials.create(ecKeyPair), BCConstant.gasPrice, BCConstant.gasLimit);

			List<NewSignaturesEventEventResponse> newSignaturesList = evidence.getNewSignaturesEventEvents(receipt);
			logger.info("depot newSignaturesList.size:{}", newSignaturesList.size());
			for (NewSignaturesEventEventResponse newSignatures : newSignaturesList) {
				EvidenceInfo evidenceInfo = new EvidenceInfo();
				evidenceInfo.setBlockHeight(blockNumber.intValue());
				evidenceInfo.setEvent("newSignaturesEvent");
				evidenceInfo.setEvidenceId(newSignatures.id.getValue());
				evidenceInfo.setEvidenceHash(newSignatures.evi.getValue());
				SignatureData signatureData = new SignatureData((byte) newSignatures.v.getValue().intValue(), newSignatures.r.getValue(), newSignatures.s.getValue());
				String signData = Tools.signatureDataToString(signatureData);
				evidenceInfo.setSignData(signData);
				evidenceInfo.setFactoryAddress(newSignatures.addr.toString());
				evidenceInfoDao.insert(evidenceInfo);
				logger.info("depot newSignaturesEvent:{}", evidenceInfo.toString());
			}
			return true;
		} catch (Exception e) {
			logger.error("handleReceipt OnError, {}", e.getMessage());
			return false;
		}
	}
    ```

### 5.7、编译DepotSample工程

- 通过gradle run编译DepotSample工程，生成depot文件夹，将其打包部署到Linux。部署前请确认是否正确配置区块链节点信息和数据库信息。

    ```
	编译生成的depot目录：
	    depot/apps：depot工程编译后生成的jar包
	    depot/conf：depot工程jar包依赖的配置文件
	    depot/db：depot工程表信息
	    depot/lib：depot工程依赖的jar包
	    control.sh：执行脚本
	    
	区块链节点信息：depot/conf/applicationContext.xml
	数据库信息：depot/conf/db.properties
    ```

### 5.8、连接数据库并初始化表

1. 在自己的数据库新建数据库（例子：depot）。
2. （存证业务跳过这一步）进入/depot/db/目录，在depot_tables.list里添加要初始化的表文件名，在depot_tables.sh里配置数据库信息（参考如下）：

    ```
    depot_tables.list：
        #!/bin/sh
        #blockheight表为默认表，不可删除
        source blockheight.sql;
        #业务数据表部分，根据实际业务进行修改
        source t_evidence_info.sql;
        
    depot_tables.sh：
        #!/bin/sh
		#dbIP
		IP="127.0.0.1"
		#dbPORT
		PORT="3306"
		#dbUser
		DBUSER="root"
		#dbPass
		PASSWD="123456"
		#dbName
		DBNAME="depot"
		
		#connect to database then execute init
		cat depot_tables.list | mysql --user=$DBUSER --password=$PASSWD --host=$IP --database=$DBNAME --port=$PORT --default-character-set=utf8;
		
		exit
    ```
    
3. 在/depot/db/目录下执行以下命令，即可在对应数据库初始化表（警告可忽略）。

    ```
    ./depot_tables.sh
    ```

### 5.9、启动和停止服务

- 进入/depot目录，启动depot服务。启动后，服务会定时查询块高信息，当有交易发生，块高增加时，会通过接口实现类DepotFacadeImpl解析交易回执信息，如果交易回执信息中存在埋点事件，会将埋点业务数据解析存入数据库。

    ```
    启动depot服务：./control.sh start
    停止depot服务：./control.sh stop
    重启depot服务：./control.sh restart
    ```
    
### 5.10、向区块链发起业务交易

- 开发者根据自己的业务发起交易。
- 这里以存证案例的创建证据（newEvidence）业务为例，使用存证案例工具包evidence_toolkit发起交易。发起交易前先确认evidence_toolkit/conf/applicationContext.xml文件中是否正确配置区块链节点信息（参照4.3）。

    ```
	进入/evidence_toolkit/bin目录，执行以下命令部署存证工厂合约：
	./evidence deploy user.jks '123456' '123456'

	上个命令会返回一个工厂合约地址，替换以下命令中的deployAddress，发起交易，新建证据：
	./evidence new user.jks '123456' '123456' 'deployAddress' '1712071OA03998263894637100018685' '5e79e0e7a16b7804a59ad3de9b0ec0f817d225c24bb6f34a7278756fb45df724'
    ```
    
### 5.11、存储结果

- 存证业务示例会将埋点数据存入t_evidence_info表中。<br>
![查询结果](/file/result.png)

### 六、日志和数据查看

1. 日志查看<br>

  depot服务启动后，会在/depot/logs目录下生成日志文件depot.log，通过以下命令即可查看相关日志信息。

    ```
    查看日志：tail -f depot.log
    ```
    
2. 数据查看<br>
  使用以下命令配置数据库信息后，连接数据库，即可查询相关数据信息：

    ```
    mysql -h ip -P port -u user -ppassword
    例子：mysql -h 127.0.0.1 -P 3306 -u root -p123456
    ```

### 七、常见问题

1. 执行命令时提示“：没有那个文件或目录”<br>
  可能是Windows文件和Linux文件会有差异，特别是用tab代替空格这种情况会造成脚本无法执行，使用以下命令进行修改：

    ```
    dos2unix 文件名
    ```

2. 执行命令时提示“权限不够”
  使用以下命令进行授权

    ```
    chmod +x 文件名
    ```

### 八、实际业务需要考虑的一些问题
1. 导出的等幂。
2. 按策略多活加速导出。
3. depot数据和区块链对账保证导出数据和链上的一致性。

###  九、[FISCO-BCOS中client.keystore 的生成方法](https://github.com/FISCO-BCOS/web3sdk/issues/20)
###  十、[web3sdk的使用方式](https://github.com/FISCO-BCOS/web3sdk)
