package main.java.busqueda;

import main.java.util.ConfigurationParametersManager;

public class SqlBusquedaDaoFactory {
	
	private final static String CLASS_NAME_PARAMETER = "SqlEventDaoFactory.className";
    private static SqlBusquedaDao dao = null;
    
    private SqlBusquedaDaoFactory () {
    }
    
    @SuppressWarnings("rawtypes")
	private static SqlBusquedaDao getInstance() {
        try {
            String daoClassName = ConfigurationParametersManager.getParameter(CLASS_NAME_PARAMETER);
            Class daoClass = Class.forName(daoClassName);
            return (SqlBusquedaDao) daoClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public synchronized static SqlBusquedaDao getDao() {
        if (dao == null) {
            dao = getInstance();
        }
        return dao;
    }
}
