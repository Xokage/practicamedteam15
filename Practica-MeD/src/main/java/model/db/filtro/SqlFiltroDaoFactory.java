package main.java.model.db.filtro;

import main.java.util.ConfigurationParametersManager;

public class SqlFiltroDaoFactory {
	
	private final static String CLASS_NAME_PARAMETER = "SqlFiltroDaoFactory.className";
    private static SqlFiltroDao dao = null;
    
    private SqlFiltroDaoFactory () {
    }
    
    @SuppressWarnings("rawtypes")
	private static SqlFiltroDao getInstance() {
		try {
			String daoClassName = ConfigurationParametersManager.getParameter(CLASS_NAME_PARAMETER);
            Class daoClass = Class.forName(daoClassName);
            return (SqlFiltroDao) daoClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public synchronized static SqlFiltroDao getDao() {
        if (dao == null) {
            dao = getInstance();
        }
        return dao;
    }
}
