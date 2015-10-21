package com.twinlin.mlm.execption;

public class DataRedundancyException extends RuntimeException
{
	private static final long serialVersionUID = -8558594697648711230L;


	public DataRedundancyException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public DataRedundancyException(String message)
	{
		super(message);
	}
	
	public DataRedundancyException(Throwable cause)
	{
		super(cause);
	}
}