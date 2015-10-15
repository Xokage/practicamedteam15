package main.java.model.db.hotel;

import main.java.util.ConfigurationParametersManager;

public class SqlHotelDaoFactory {
	
	private final static String CLASS_NAME_PARAMETER = "SqlHotelDaoFactory.className";
    private static SqlHotelDao dao = null;
    
    private SqlHotelDaoFactory () {
    }
    
    @SuppressWarnings("rawtypes")
	private static SqlHotelDao getInstance() {
		try {
			String daoClassName = ConfigurationParametersManager.getParameter(CLASS_NAME_PARAMETER);
            Class daoClass = Class.forName(daoClassName);
            return (SqlHotelDao) daoClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public synchronized static SqlHotelDao getDao() {
        if (dao == null) {
            dao = getInstance();
        }
        return dao;
    }
}
