package com.core.Exception;

public class ErrorsUtil {

	/**
	 * 构造一个标准格式的错误信息
	 *     [errorCode] [origin] [param]
	 * 例：[ITEM.ERR.NOEXIST] [#GS.ItemLogicalProcessor.onRepair] [bagId:1001,bagIndex:2]
	 * 
	 * @author sd 2009-10-20
	 * @param errorCode
	 *             错误代码 @see {@link CommonErrorLogInfo}
	 * @param origin
	 *             错误产生地 #包缩写(GS,WS,LS,DBS,CORE,LOG).类名.方法名
	 * @param param
	 *             需要记录实时数据
	 * @return
	 */
	public static String error(String errorCode, String origin, String param) {
		StringBuilder _errorStr = new StringBuilder("[").append(errorCode).append("] [").append(origin).append("]");
		if(param != null && param.length() > 0) {
			_errorStr.append(" [").append(param).append("]");
		}
		return _errorStr.toString();
	}
	
	/**
	 * 记录所有错误堆栈信息
	 * @param ex 错误信息
	 * @return
	 */
	public static String errorAllThrowableStackMessage(String errorCode, Throwable ex) {
		StringBuffer errorString = new StringBuffer();
		errorString.append("ErrorCode = " + errorCode + "\n");
		if (ex != null) {
			errorString.append("ExceptionClazz = " + ex.getClass().getName() + "\n");
			errorString.append("Message = " + ex.getMessage() + "\n");
			errorString.append("Cause = " + ((ex.getCause() == null) ? "null" : ex.getCause()) + "\n");
			errorString.append("StackTrace = {\n");
			StackTraceElement[] stackTraceElements = ex.getStackTrace();
			if (stackTraceElements != null && stackTraceElements.length != 0) {
				for (StackTraceElement stackTraceElement : stackTraceElements) {
					errorString.append("    " + stackTraceElement + "\n");
				}
			}
			errorString.append("}");
		}
		return errorString.toString();
	}

}
