package com.example.tokoin.contracts;

import android.os.AsyncTask;

import com.example.tokoin.SolidityReview;

import android.os.Environment;
import android.util.Log;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import java.io.File;
import java.math.BigDecimal;

public class DeployContracts {

    public void run() throws Exception {

        // We start by creating a new web3j instance to connect to remote nodes on the network.
        // Note: if using web3j Android, use Web3jFactory.build(...
        Web3j web3j = Web3j.build(new HttpService(
                "https://rinkeby.infura.io/v3/fa43c71be4304c2c921ffa47cf288b1f"));  // FIXME: Enter your Infura token here;
        Log.i("connect","Connected to Ethereum client version: "
                + web3j.web3ClientVersion().send().getWeb3ClientVersion());

        // We then need to load our Ethereum wallet file
        // FIXME: Generate a new wallet file using the web3j command line tools https://docs.web3j.io/command_line.html

        Credentials credentials = Credentials.create("0xffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        Log.i("load","Credentials loaded");

        // FIXME: Request some Ether for the Rinkeby test network at https://www.rinkeby.io/#faucet
        Log.i("send","Sending 1 Wei ("
                + Convert.fromWei("1", Convert.Unit.ETHER).toPlainString() + " Ether)");
        TransactionReceipt transferReceipt = Transfer.sendFunds(
                web3j, credentials,
                "0xc6d264611b0b8fea67fd6403f3633139aa746495",  // you can put any address here
                BigDecimal.ONE, Convert.Unit.WEI)  // 1 wei = 10^-18 Ether
                .send();
        Log.i("complete","Transaction complete, view it at https://rinkeby.etherscan.io/tx/"
                + transferReceipt.getTransactionHash());

        // Now lets deploy a smart contract
        Log.i("deploy","Deploying smart contract");
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        Tokoin contract = Tokoin.deploy(
                web3j,
                credentials,
                contractGasProvider
        ).send();

        String contractAddress = contract.getContractAddress();
        Log.i("deployed","Smart contract deployed to address " + contractAddress);
        Log.i("view","View contract at https://rinkeby.etherscan.io/address/" + contractAddress);
    }
}
