/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.hsy.blockChain.rest.inner;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ower.dsyz.blockChain.other.Transaction;
import com.ower.dsyz.blockChain.service.IBlockChainService;
import com.ower.dsyz.common.core.annotation.EnableValid;
import com.ower.dsyz.common.core.response.CustomResponse;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年3月21日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@RestController
@RequestMapping("/inner/blockChainService")
public class BlockChainResource {

    private Logger  logger = LoggerFactory.getLogger(BlockChainResource.class);
    
    @Resource
    private IBlockChainService blockChainService;
    
    /**
     * 交易
     * @param transaction
     * @return Object
     */
    @RequestMapping("createTransaction")
    public Object createTransaction(@RequestBody @EnableValid Transaction transaction){
        logger.info("创建交易，{}",transaction);
        return CustomResponse.success("当前交易加载区块[ "+blockChainService.newTransactions(transaction)+" ]上"); 
    }
    
    /**
     * 创建区块
     * @return Object
     */
    @RequestMapping("createBlock")
    public Object createBlock(){
        logger.info("创建区块");
        Long proof = blockChainService.proofOfWork();
        return blockChainService.createBlock(proof);
    }
    
    /**
     * 查找区块
     * @return Object
     */
    @RequestMapping("queryBlockChain")
    public Object queryBlockChain(){
        return blockChainService.getBlocks();
    }
}
