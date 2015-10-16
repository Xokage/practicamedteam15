package main.java.model.db.reserva;


import main.java.util.ConfigurationParametersManager;

public class SqlReservaDaoFactory {
	
	private final static String CLASS_NAME_PARAMETER = "SqlReservaDaoFactory.className";
    private static SqlReservaDao dao = null;
    
    private SqlReservaDaoFactory () {
    }
    
    @SuppressWarnings("rawtypes")
	private static SqlReservaDao getInstance() {
		try {
			String daoClassName = ConfigurationParametersManager.getParameter(CLASS_NAME_PARAMETER);
            Class daoClass = Class.forName(daoClassName);
            return (SqlReservaDao) daoClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public synchronized static SqlReservaDao getDao() {
        if (dao == null) {
            dao = getInstance();
        }
        return dao;
    }
}
