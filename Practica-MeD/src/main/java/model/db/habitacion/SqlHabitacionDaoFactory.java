package main.java.model.db.habitacion;

import main.java.util.ConfigurationParametersManager;

public class SqlHabitacionDaoFactory {
	
	private final static String CLASS_NAME_PARAMETER = "SqlHabitacionDaoFactory.className";
    private static SqlHabitacionDao dao = null;
    
    private SqlHabitacionDaoFactory () {
    }
    
    @SuppressWarnings("rawtypes")
	private static SqlHabitacionDao getInstance() {
		try {
			String daoClassName = ConfigurationParametersManager.getParameter(CLASS_NAME_PARAMETER);
            Class daoClass = Class.forName(daoClassName);
            return (SqlHabitacionDao) daoClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public synchronized static SqlHabitacionDao getDao() {
        if (dao == null) {
            dao = getInstance();
        }
        return dao;
    }
}
