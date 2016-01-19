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

import java.util.Date;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.DialogBox;

public class HysbysAngenNewidFfurfweddiad extends DialogBox{

	public HysbysAngenNewidFfurfweddiad(){
		
		setText("Cymreigio eich porwr");
				
		setAnimationEnabled(true);
		setGlassEnabled(true);
		setWidth("500px");

		CynnwysHysbys cynnwys = new CynnwysHysbys();
		
		ClickHandler closeClickHandler = new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
			
		};
		
		ClickHandler rejectClickHandler = new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// ysgrifennu cookie. Dod i ben mewn flwyddyn.
				Date expires = new Date(new Date().getTime() + ((60*1000)*60*24*365));				
				Cookies.setCookie(CymreigioPorwyr.COOKIE_NAME, "REJECT", expires);						
				hide();
			}
			
		};
								
		cynnwys.addCloseClickHandler(closeClickHandler);
		cynnwys.addRejectClickHandler(rejectClickHandler);
							
		setWidget(cynnwys);
				
	}
	
	public void scheduledCenter(){
		
		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
		    public void execute() {		    	
		    	center();		        		    	
		    }
		});
		
	}
		
}
