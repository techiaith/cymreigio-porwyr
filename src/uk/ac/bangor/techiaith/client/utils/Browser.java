package uk.ac.bangor.techiaith.client.utils;

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

import com.google.gwt.user.client.Window.Navigator;

public class Browser {
	
	private static String [] SUPPORTED_OS = {"Mac OS X", "Windows NT", "Linux x86", "Linux i686"};
	
	public enum SupportedBrowser {
		
		INTERNETEXPLORER("MSIE|Trident"),
		FIREFOX("Firefox"),
		CHROME("Chrome"),
		NOTSUPPORTED("NotSupported"); // gobeithio bydd yr un useragent string ddim yn cynnwys yr ymadrodd hon.
		
		private String uaName;
		SupportedBrowser(String uaName){
			this.uaName=uaName;
		}
						
	}
	
		
	public static String getBrowserSpecificVideoId(){
		
		SupportedBrowser browser=getBrowser();
		if (browser==SupportedBrowser.FIREFOX)
			return "90020937";
		else if (browser==SupportedBrowser.CHROME)
			return "90030693";
		else if (browser==SupportedBrowser.INTERNETEXPLORER)
			return "90068451";
		
		return "";
		
	}
		
	public static SupportedBrowser getBrowser(){
		
		String userAgent = Navigator.getUserAgent();
		userAgent=userAgent.toLowerCase();
		
		// 
		// parhau dim ond os mae ar OS rydyn yn gydnabod		
		for (String os: SUPPORTED_OS){
			if (userAgent.toLowerCase().indexOf(os.toLowerCase()) != -1){
				for (SupportedBrowser sb: SupportedBrowser.values()){					
					if (userAgent.toLowerCase().indexOf(sb.uaName.toLowerCase()) != -1) {
						return sb;						
					} else if (sb.uaName.indexOf("|")!=-1){
						String [] uaNames = sb.uaName.split("\\|");
						for (String name: uaNames){
							if (userAgent.toLowerCase().indexOf(name.toLowerCase())!=-1) {
								return sb;
							}		
						}
					}
				}
			}
		}

		return SupportedBrowser.NOTSUPPORTED;		
				
	}	
						
}
