package kejamart.dao;

import java.util.List;

import kejamart.model.RangeAmount;

public interface RangeAmountDAO {
	

	public RangeAmount getRangeAmountById(int id) throws Exception;
	public void addRangeAmount(RangeAmount rangeAmount);
	public void deleteRangeAmount(RangeAmount rangeAmount);
	public List<RangeAmount> getAllRangeAmount();
	public int countRangeAmount();
	public List<RangeAmount> getRangeAmountForId(int id);
	public List<RangeAmount> getCommercialRange();
	public List<RangeAmount> getResidentialRange();

}