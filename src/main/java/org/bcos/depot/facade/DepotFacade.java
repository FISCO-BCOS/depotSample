package org.bcos.depot.facade;

import java.math.BigInteger;

import org.springframework.stereotype.Component;

import org.bcos.web3j.protocol.Web3j;
import org.bcos.web3j.protocol.core.methods.response.TransactionReceipt;

@Component
public interface DepotFacade {
	
	public boolean handleReceipt(TransactionReceipt receipt, Web3j web3j, BigInteger blockNumber);
}
