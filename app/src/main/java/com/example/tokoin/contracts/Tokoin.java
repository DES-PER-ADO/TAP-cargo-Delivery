package com.example.tokoin.contracts;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.16.
 */
@SuppressWarnings("rawtypes")
public class Tokoin extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b03191633179055611220806100326000396000f3fe608060405234801561001057600080fd5b50600436106100f55760003560e01c80636d4ce63c11610097578063d3499d8511610066578063d3499d851461031a578063e043d1cc14610337578063e72d10df1461033f578063e985e9c51461036b576100f5565b80636d4ce63c1461025457806370a082311461025c578063cb71253514610294578063ce34fba4146102ca576100f5565b8063271a05f6116100d3578063271a05f61461019757806349cdf969146101e15780636352211e1461022f57806369245bd91461024c576100f5565b8063081812fc146100fa578063095ea7b31461013357806323b872dd14610161575b600080fd5b6101176004803603602081101561011057600080fd5b5035610399565b604080516001600160a01b039092168252519081900360200190f35b61015f6004803603604081101561014957600080fd5b506001600160a01b0381351690602001356103fb565b005b61015f6004803603606081101561017757600080fd5b506001600160a01b03813581169160208101359091169060400135610519565b6101cd600480360360608110156101ad57600080fd5b506001600160a01b0381358116916020810135909116906040013561056f565b604080519115158252519081900360200190f35b6101cd600480360360e08110156101f757600080fd5b508035906020810135906040810135906060810135906001600160a01b03608082013581169160a08101359091169060c00135610650565b6101176004803603602081101561024557600080fd5b5035610750565b6101cd6107aa565b6101176107e9565b6102826004803603602081101561027257600080fd5b50356001600160a01b03166107f8565b60408051918252519081900360200190f35b6101cd600480360360608110156102aa57600080fd5b506001600160a01b0381358116916020810135909116906040013561085b565b6101cd600480360360e08110156102e057600080fd5b506001600160a01b03813581169160208101359160408201359160608101359160808201359160a081013582169160c09091013516610991565b6101cd6004803603602081101561033057600080fd5b5035610c09565b61015f610c42565b6101cd6004803603604081101561035557600080fd5b506001600160a01b038135169060200135610cad565b6101cd6004803603604081101561038157600080fd5b506001600160a01b0381358116916020013516610d4f565b60006103a482610d7d565b6103df5760405162461bcd60e51b8152600401808060200182810382526032815260200180610fd86032913960400191505060405180910390fd5b506000908152600260205260409020546001600160a01b031690565b600061040682610750565b9050806001600160a01b0316836001600160a01b031614156104595760405162461bcd60e51b8152600401808060200182810382526027815260200180610f826027913960400191505060405180910390fd5b336001600160a01b038216148061047557506104758133610d4f565b6104b05760405162461bcd60e51b815260040180806020018281038252603e8152602001806110a5603e913960400191505060405180910390fd5b60008281526002602090815260409182902080546001600160a01b0319166001600160a01b03878116918217909255835186815293519093918516927f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925928290030190a3505050565b6105233382610d9a565b61055e5760405162461bcd60e51b81526004018080602001828103825260378152602001806111b56037913960400191505060405180910390fd5b61056983838361085b565b50505050565b6000836001600160a01b031661058483610750565b6001600160a01b0316146105c95760405162461bcd60e51b815260040180806020018281038252602e81526020018061100a602e913960400191505060405180910390fd5b6105d282610c09565b1515600114806105ed57506105e78483610cad565b15156001145b15610645576105fd84848461085b565b506040805183815290516001600160a01b038616917f2b4a72413ec6fe03988d2d23c26fc52bc0ba4a2bbc962222539893e95d94dcc8919081900360200190a2506001610649565b5060005b9392505050565b600080546001600160a01b031633141561072357506040805161010081018252888152602080820189815242838501908152606084018a8152608085018a81526001600160a01b03808b1660a088019081528a821660c0890190815260e089018b815260008c81526004998a90529a909a209851895595516001808a01919091559451600289015592516003880155905194860194909455516005850180549185166001600160a01b03199283161790559151600685018054919094169216919091179091559151600790910155610745565b6107456040518060600160405280603a8152602001611038603a913933610e36565b979650505050505050565b6000818152600160205260408120546001600160a01b0316806107a45760405162461bcd60e51b815260040180806020018281038252602f815260200180610fa9602f913960400191505060405180910390fd5b92915050565b600080546001600160a01b03163314156107c15733ff5b6107e36040518060600160405280603a8152602001611038603a913933610e36565b50600090565b6000546001600160a01b031690565b60006001600160a01b03821661083f5760405162461bcd60e51b81526004018080602001828103825260308152602001806111336030913960400191505060405180910390fd5b506001600160a01b031660009081526005602052604090205490565b6000836001600160a01b031661087083610750565b6001600160a01b0316146108b55760405162461bcd60e51b81526004018080602001828103825260308152602001806111636030913960400191505060405180910390fd5b6001600160a01b0383166108fa5760405162461bcd60e51b815260040180806020018281038252602a815260200180611109602a913960400191505060405180910390fd5b61090382610eed565b6001600160a01b03808516600081815260056020908152604080832080546000190190559387168083528483208054600190810190915587845282529184902080546001600160a01b031916831790558351868152935191937fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef929081900390910190a35060019392505050565b6000600161099d610f2a565b6040518061010001604052808a8152602001898152602001428152602001888152602001878152602001866001600160a01b03168152602001856001600160a01b0316815260200183815250905060006001600160a01b03168a6001600160a01b03161415610a3d5760405162461bcd60e51b81526004018080602001828103825260268152602001806110e36026913960400191505060405180910390fd5b610a4682610d7d565b15610a825760405162461bcd60e51b81526004018080602001828103825260228152602001806111936022913960400191505060405180910390fd5b896001600084815260200190815260200160002060006101000a8154816001600160a01b0302191690836001600160a01b03160217905550600560008b6001600160a01b03166001600160a01b031681526020019081526020016000206000815480929190600101919050555080600360008c6001600160a01b03166001600160a01b03168152602001908152602001600020600082015181600001556020820151816001015560408201518160020155606082015181600301556080820151816004015560a08201518160050160006101000a8154816001600160a01b0302191690836001600160a01b0316021790555060c08201518160060160006101000a8154816001600160a01b0302191690836001600160a01b0316021790555060e08201518160070155905050896001600160a01b03168a6001600160a01b03167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef846040518082815260200191505060405180910390a35060019998505050505050505050565b600081815260046020819052604082209081015460029091015480820142101580610c3a5750818101610258014211155b949350505050565b6000546001600160a01b0316331415610c8957610c846040518060400160405280600d81526020016c024b7aa1030b1ba34b7b7399d1609d1b81525033610e36565b610cab565b610cab6040518060600160405280603a8152602001611038603a913933610e36565b565b6000826001600160a01b0316610cc283610750565b6001600160a01b031614610d075760405162461bcd60e51b815260040180806020018281038252602e81526020018061100a602e913960400191505060405180910390fd5b6040805183815290516001600160a01b038516917f2b4a72413ec6fe03988d2d23c26fc52bc0ba4a2bbc962222539893e95d94dcc8919081900360200190a250600192915050565b6001600160a01b03918216600090815260066020908152604080832093909416825291909152205460ff1690565b6000908152600160205260409020546001600160a01b0316151590565b6000610da582610d7d565b610de05760405162461bcd60e51b81526004018080602001828103825260338152602001806110726033913960400191505060405180910390fd5b6000610deb83610750565b9050806001600160a01b0316846001600160a01b03161480610e265750836001600160a01b0316610e1b84610399565b6001600160a01b0316145b80610c3a5750610c3a8185610d4f565b7f62ddffe5b5108385f7a590f100e1ee414ad9551a31f089e64e82998440785e1e82826040518080602001836001600160a01b03166001600160a01b03168152602001828103825284818151815260200191508051906020019080838360005b83811015610eae578181015183820152602001610e96565b50505050905090810190601f168015610edb5780820380516001836020036101000a031916815260200191505b50935050505060405180910390a15050565b6000818152600260205260409020546001600160a01b031615610f2757600081815260026020526040902080546001600160a01b03191690555b50565b604051806101000160405280600081526020016000815260200160008152602001600081526020016000815260200160006001600160a01b0316815260200160006001600160a01b0316815260200160008152509056fe546f6b6f696e5e302e312e303a20617070726f76616c20746f2063757272656e74206f776e6572546f6b6f696e5e302e312e303a206f776e657220717565727920666f72206e6f6e6578697374656e7420746f6b656e546f6b6f696e5e302e312e303a20617070726f76656420717565727920666f72206e6f6e6578697374656e7420746f6b656e546f6b6f696e5e302e312e303a2072656465656d206f6620746f6b6f696e2074686174206973206e6f74206f776e546f6b6f696e5e302e312e303a206465636f6e737472756374696f6e206f6620636f6e74726163742074686174206973206e6f74206f776e3a20546f6b6f696e5e302e312e303a206f70657261746f7220717565727920666f72206e6f6e6578697374656e7420746f6b6f696e546f6b6f696e5e302e312e303a20617070726f76652063616c6c6572206973206e6f74206f776e6572206e6f7220617070726f76656420666f7220616c6c546f6b6f696e5e302e312e303a206d696e7420746f20746865207a65726f2061646472657373546f6b6f696e5e302e312e303a207472616e7366657220746f20746865207a65726f2061646472657373546f6b6f696e5e302e312e303a2062616c616e636520717565727920666f7220746865207a65726f2061646472657373546f6b6f696e5e302e312e303a207472616e73666572206f6620746f6b6f696e2074686174206973206e6f74206f776e546f6b6f696e5e302e312e303a20746f6b656e20616c7265616479206d696e746564546f6b6f696e5e302e312e303a207472616e736665722063616c6c6572206973206e6f74206f776e6572206e6f7220617070726f766564a265627a7a7231582080655f3528c8e6124e7f11c18b7d505724302160ab4a1ba17e5f2bb65bc2239064736f6c63430005110032";

    public static final String FUNC__ACCESSOUTPUT = "_accessOutput";

    public static final String FUNC__ACCESSREVOCATION = "_accessRevocation";

    public static final String FUNC__ACCESSRULE = "_accessRule";

    public static final String FUNC__MINT = "_mint";

    public static final String FUNC__REDEEMCHECKCONDITION = "_redeemCheckCondition";

    public static final String FUNC__REDEEMVERIFICATION = "_redeemVerification";

    public static final String FUNC__TOKOINREDEEM = "_tokoinRedeem";

    public static final String FUNC__TRANSFERFROM = "_transferFrom";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_GET = "get";

    public static final String FUNC_GETAPPROVED = "getApproved";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_OWNEROF = "ownerOf";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event LOGADDRESS_EVENT = new Event("LogAddress", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event TOKOINID_EVENT = new Event("TokoinId", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TOKOIN_EVENT = new Event("tokoin", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TOKOINREDEEM_EVENT = new Event("tokoinRedeem", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Tokoin(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Tokoin(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Tokoin(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Tokoin(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._approved = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._approved = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<LogAddressEventResponse> getLogAddressEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGADDRESS_EVENT, transactionReceipt);
        ArrayList<LogAddressEventResponse> responses = new ArrayList<LogAddressEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogAddressEventResponse typedResponse = new LogAddressEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.s = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.x = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LogAddressEventResponse> logAddressEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, LogAddressEventResponse>() {
            @Override
            public LogAddressEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LOGADDRESS_EVENT, log);
                LogAddressEventResponse typedResponse = new LogAddressEventResponse();
                typedResponse.log = log;
                typedResponse.s = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.x = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<LogAddressEventResponse> logAddressEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOGADDRESS_EVENT));
        return logAddressEventFlowable(filter);
    }

    public List<TokoinIdEventResponse> getTokoinIdEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TOKOINID_EVENT, transactionReceipt);
        ArrayList<TokoinIdEventResponse> responses = new ArrayList<TokoinIdEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TokoinIdEventResponse typedResponse = new TokoinIdEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TokoinIdEventResponse> tokoinIdEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TokoinIdEventResponse>() {
            @Override
            public TokoinIdEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TOKOINID_EVENT, log);
                TokoinIdEventResponse typedResponse = new TokoinIdEventResponse();
                typedResponse.log = log;
                typedResponse._tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TokoinIdEventResponse> tokoinIdEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TOKOINID_EVENT));
        return tokoinIdEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public List<TokoinEventResponse> getTokoinEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TOKOIN_EVENT, transactionReceipt);
        ArrayList<TokoinEventResponse> responses = new ArrayList<TokoinEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TokoinEventResponse typedResponse = new TokoinEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TokoinEventResponse> tokoinEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TokoinEventResponse>() {
            @Override
            public TokoinEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TOKOIN_EVENT, log);
                TokoinEventResponse typedResponse = new TokoinEventResponse();
                typedResponse.log = log;
                typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TokoinEventResponse> tokoinEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TOKOIN_EVENT));
        return tokoinEventFlowable(filter);
    }

    public List<TokoinRedeemEventResponse> getTokoinRedeemEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TOKOINREDEEM_EVENT, transactionReceipt);
        ArrayList<TokoinRedeemEventResponse> responses = new ArrayList<TokoinRedeemEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TokoinRedeemEventResponse typedResponse = new TokoinRedeemEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TokoinRedeemEventResponse> tokoinRedeemEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TokoinRedeemEventResponse>() {
            @Override
            public TokoinRedeemEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TOKOINREDEEM_EVENT, log);
                TokoinRedeemEventResponse typedResponse = new TokoinRedeemEventResponse();
                typedResponse.log = log;
                typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TokoinRedeemEventResponse> tokoinRedeemEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TOKOINREDEEM_EVENT));
        return tokoinRedeemEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> _accessOutput() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC__ACCESSOUTPUT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> _accessRevocation() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC__ACCESSREVOCATION, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> _accessRule(BigInteger _itemId, BigInteger _redeemTimes, BigInteger _dateToDelivery, BigInteger _DeliveryTimeInterval, String _client, String _owner, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC__ACCESSRULE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_itemId), 
                new org.web3j.abi.datatypes.generated.Uint256(_redeemTimes), 
                new org.web3j.abi.datatypes.generated.Uint256(_dateToDelivery), 
                new org.web3j.abi.datatypes.generated.Uint256(_DeliveryTimeInterval), 
                new org.web3j.abi.datatypes.Address(160, _client), 
                new org.web3j.abi.datatypes.Address(160, _owner), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> _mint(String to, BigInteger _itemId, BigInteger _redeemTimes, BigInteger _dateToDelivery, BigInteger _DeliveryTimeInterval, String _client, String _owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC__MINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(_itemId), 
                new org.web3j.abi.datatypes.generated.Uint256(_redeemTimes), 
                new org.web3j.abi.datatypes.generated.Uint256(_dateToDelivery), 
                new org.web3j.abi.datatypes.generated.Uint256(_DeliveryTimeInterval), 
                new org.web3j.abi.datatypes.Address(160, _client), 
                new org.web3j.abi.datatypes.Address(160, _owner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> _redeemCheckCondition(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__REDEEMCHECKCONDITION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> _redeemVerification(String from, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC__REDEEMVERIFICATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> _tokoinRedeem(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC__TOKOINREDEEM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> _transferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC__TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String _owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> get() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getApproved(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPROVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> isApprovedForAll(String owner, String operator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISAPPROVEDFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, operator)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> ownerOf(BigInteger _tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNEROF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Tokoin load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Tokoin(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Tokoin load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Tokoin(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Tokoin load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Tokoin(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Tokoin load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Tokoin(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Tokoin> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Tokoin.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Tokoin> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Tokoin.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Tokoin> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Tokoin.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Tokoin> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Tokoin.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String _owner;

        public String _approved;

        public BigInteger _tokenId;
    }

    public static class LogAddressEventResponse extends BaseEventResponse {
        public String s;

        public String x;
    }

    public static class TokoinIdEventResponse extends BaseEventResponse {
        public BigInteger _tokenId;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String _from;

        public String _to;

        public BigInteger _tokenId;
    }

    public static class TokoinEventResponse extends BaseEventResponse {
        public String _owner;

        public BigInteger _tokenId;
    }

    public static class TokoinRedeemEventResponse extends BaseEventResponse {
        public String _owner;

        public BigInteger _tokenId;
    }
}
