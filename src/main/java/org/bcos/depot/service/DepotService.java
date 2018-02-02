package org.bcos.depot.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.bcos.depot.entity.Blockheight;
import org.bcos.depot.facade.impl.DepotFacadeImpl;
import org.bcos.depot.mapper.BlockheightDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import org.bcos.web3j.protocol.Web3j;
import org.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.bcos.web3j.protocol.core.methods.response.EthBlock;
import org.bcos.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.bcos.web3j.protocol.core.methods.response.TransactionReceipt;

@Service
@EnableScheduling
public class DepotService implements InitializingBean {
	private static Logger logger = LoggerFactory.getLogger(DepotService.class);

	public void afterPropertiesSet() throws Exception {
		logger.info("LifeCycleBean initializing...");
		try {
			service.run();
			Thread.sleep(3000);
			ChannelEthereumService channelEthereumService = new ChannelEthereumService();
			channelEthereumService.setTimeout(3000);
			channelEthereumService.setChannelService(service);
			web3j = Web3j.build(channelEthereumService);
		} catch (Exception e) {
			logger.error("depot OnError, {}", e.getMessage());
		}
	}

	@Scheduled(fixedRate = 5000)
	void handle() {
		try {
			BigInteger blockNumber = web3j.ethBlockNumber().send().getBlockNumber();
			logger.info("链上块高eth blockNumber:{}", blockNumber);
			Blockheight blockheight = blockheightDao.selectByPrimaryKey((long) 1);
			int height = blockheight.getHeight();
			logger.info("DB块高db height:{}", height);
			boolean handleResult = false;
			while (height++ < blockNumber.intValue()) {
				BigInteger bigBlockHeight = new BigInteger(Integer.toString(height));
				EthBlock ethBlock = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(bigBlockHeight), false).send();
				EthBlock.Block block = ethBlock.getBlock();
				List<EthBlock.TransactionResult> transactionResults = block.getTransactions();
				logger.info("depot transactionResults.size:{}", transactionResults.size());
				for (EthBlock.TransactionResult result : transactionResults) {
					EthGetTransactionReceipt ethGetTransactionReceipt = web3j.ethGetTransactionReceipt((String) result.get()).send();
					Optional<TransactionReceipt> opt = ethGetTransactionReceipt.getTransactionReceipt();
					if (opt.isPresent()) {
						handleResult = depotFacade.handleReceipt(opt.get(), web3j, bigBlockHeight);
						logger.info("DepotService blockNumber:{} handleResult:{}", bigBlockHeight, handleResult);
					}
				}
				if (handleResult) {
					blockheight.setHeight(blockheight.getHeight() + 1);
					blockheightDao.updateByPrimaryKey(blockheight);
				} else {
					return;
				}
			}
		} catch (Exception e) {
			logger.error("depot OnError, {}", e.getMessage());
		}
	}

	@Autowired
	private BlockheightDao blockheightDao;

	@Autowired
	private org.bcos.channel.client.Service service;
	
	@Autowired
	private DepotFacadeImpl depotFacade;

	private Web3j web3j;
}
