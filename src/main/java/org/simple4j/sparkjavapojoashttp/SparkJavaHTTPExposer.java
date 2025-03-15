package org.simple4j.sparkjavapojoashttp;

import org.simple4j.wsfeeler.pojoashttp.HTTPExposer;
import org.springframework.context.ApplicationContext;

import spark.Spark;

public class SparkJavaHTTPExposer extends HTTPExposer
{

	public SparkJavaHTTPExposer(ApplicationContext context)
	{
		super(context);
	}

	@Override
	public void expose()
    {
		if(this.getListenerPortNumber() > 0)
		{
	        Spark.port(this.getListenerPortNumber());
	        Spark.threadPool(this.getListenerThreadMax(), 
	        		this.getListenerThreadMin(), this.getListenerIdleTimeoutMillis());
		}
	    Spark.post(this.getUrlBase()+"/request.json", (request, response) -> 
	    {
	        String body = request.body();
	        return processRequest(body);
	    });
    }


}
