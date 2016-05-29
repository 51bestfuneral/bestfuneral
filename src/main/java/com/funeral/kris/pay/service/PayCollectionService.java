package com.funeral.kris.pay.service;

import java.util.List;
import java.util.Map;

import com.funeral.kris.model.TFeeCollection;

public interface PayCollectionService {
	
public void initFeeCollection(String orderNo);
	
	public int completeCollection(Map<String,String> params ) throws Exception;
	
    public void deleteResource(int collectionId);
    
    public List<TFeeCollection> loadFeeCollection();
    public  TFeeCollection loadPayableFeeCollectionByOrderNo(String orderNo);
    public TFeeCollection loadFeeCollectionById(int collectionId);
    
    public void upCollection(TFeeCollection tFeeCollection);

	List<TFeeCollection> loadFeeCollectionByOrderNo(String orderNo);
	

}
