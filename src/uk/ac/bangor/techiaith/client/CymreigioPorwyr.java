package uk.ac.bangor.techiaith.client;

// Copyright (c) 2014, Prifysgol Bangor University
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without modification, 
// are permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice, this 
//	  list of conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright notice, this 
//    list of conditions and the following disclaimer in the documentation and/or 
//    other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
// ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
// WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
// IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
// INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
// NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
// PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
// WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
// POSSIBILITY OF SUCH DAMAGE.

import uk.ac.bangor.techiaith.client.utils.Browser;
import uk.ac.bangor.techiaith.client.utils.Browser.SupportedBrowser;
import uk.ac.bangor.techiaith.client.utils.Console;
import uk.ac.bangor.techiaith.client.utils.HttpHeaderJsonOverlay;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window.Navigator;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CymreigioPorwyr implements EntryPoint {

	public static final String COOKIE_NAME = "techiaith_cymreigio";
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		// penderfynu os mae'n porwr addas. 
		SupportedBrowser browser = Browser.getBrowser();
		Console.log("Porwr yw : " + browser.toString());
		
		if (browser != SupportedBrowser.NOTSUPPORTED){
							
			Console.log("Am wirio headers...");
			
			JsonpRequestBuilder jsonp = new JsonpRequestBuilder();			
	        jsonp.setTimeout(30000);   
	        
	        jsonp.requestObject("http://techiaith.bangor.ac.uk/cymreigio-server/Default.ashx",
	        		new AsyncCallback<HttpHeaderJsonOverlay>(){
	
				@Override
				public void onFailure(Throwable caught) {
					Console.log(caught.getMessage());
				}
	
				@Override
				public void onSuccess(HttpHeaderJsonOverlay result) {
																
					//
					if ((result.acceptLanguage()!=null) && (result.acceptLanguage().length()>0)){
						
						String preferredLanguage = null;
						String acceptLanguage = result.acceptLanguage();
																	
						Console.log("HTTP AcceptLanguage amrwd : " + acceptLanguage);
						
						// echdynnu ddim ond y rhan pwysicaf o'r acceptLanguage gan ein weinydd
						if (acceptLanguage.indexOf(";")>0)
							acceptLanguage = acceptLanguage.substring(0,acceptLanguage.indexOf(';'));
						
						Console.log("HTTP AcceptLanguage ieithoedd : " + acceptLanguage);
						
						// echdynnu'r pa iaith sydd gyntaf, os mae na mwy nag un.
						if (acceptLanguage.indexOf(',') > 0) {
							preferredLanguage = acceptLanguage.substring(0,acceptLanguage.indexOf(','));						
						} else {
							// dim un iaith sydd wedi ei ffurfweddu o fewn y porwr.
							preferredLanguage = acceptLanguage;
						}
						
						Console.log("Iaith blaenoriaeth : " + preferredLanguage);
						
						// os en-US yw'r unig iaith neu'r iaith cyntaf, mae'n debyg bod y defnyddiwr erioed 
						// wedi ei newid efallai bod angen eu hysbysu bod modd newid iaith y porwr i Gymraeg
						// neu eu bod angen cymorth i wneud hyn.
						//
						if (preferredLanguage.equalsIgnoreCase("en-US")) {

							// gwiro os yw'r defnyddiwr eisoes wedi gwrthod dewis gosod Cymraeg
							String previousDecision = Cookies.getCookie(COOKIE_NAME);								
							Console.log("Cookie " + COOKIE_NAME + " = " + previousDecision);
							
							if ((previousDecision == null) ||
							   ((previousDecision != null) && (previousDecision != "REJECT" ))) {

								HysbysAngenNewidFfurfweddiad hysbys = new HysbysAngenNewidFfurfweddiad();															
								hysbys.show();								
								hysbys.scheduledCenter();
							}
							
						} 
						
					}
								
				}	
				
	        });
		        					
		} else {
			Console.log("Browser not supported : " + Navigator.getUserAgent());
		}

	}
	
}
