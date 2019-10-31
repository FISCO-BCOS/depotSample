package org.bcos.depot.facade.impl;

import java.math.BigInteger;
import java.util.List;

import org.bcos.depot.contract.BCConstant;
import org.bcos.depot.contract.Evidence;
import org.bcos.depot.contract.Evidence.NewSignaturesEventEventResponse;
import org.bcos.depot.entity.EvidenceInfo;
import org.bcos.depot.facade.DepotFacade;
import org.bcos.depot.mapper.EvidenceInfoDao;
import org.bcos.depot.utils.SignatureDataUtils;
import org.bcos.web3j.crypto.Credentials;
import org.bcos.web3j.crypto.ECKeyPair;
import org.bcos.web3j.crypto.Keys;
import org.bcos.web3j.crypto.Sign.SignatureData;
import org.bcos.web3j.protocol.Web3j;
import org.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepotFacadeImpl implements DepotFacade {
	private Logger logger = LoggerFactory.getLogger(DepotFacadeImpl.class);

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
				String signData = SignatureDataUtils.signatureDataToString(signatureData);
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

	@Autowired
	private EvidenceInfoDao evidenceInfoDao;

	private Evidence evidence;
}
