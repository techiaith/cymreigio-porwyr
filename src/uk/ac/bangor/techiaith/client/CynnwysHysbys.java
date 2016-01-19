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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Widget;

public class CynnwysHysbys extends Composite {

	private static CynnwysHysbysUiBinder uiBinder = GWT.create(CynnwysHysbysUiBinder.class);
	interface CynnwysHysbysUiBinder extends UiBinder<Widget, CynnwysHysbys> {}

	@UiField Button botwmCau;
	@UiField Button botwmGwrthod;
	@UiField Frame  iframeFideo;
	
	public CynnwysHysbys() {
		
		initWidget(uiBinder.createAndBindUi(this));
		
		String videoUrl = "http://player.vimeo.com/video/" + Browser.getBrowserSpecificVideoId();
		
		iframeFideo.setUrl(videoUrl);
		iframeFideo.setWidth("400px");
		iframeFideo.setHeight("225px");
		iframeFideo.getElement().setAttribute("frameborder","0");
		iframeFideo.getElement().setAttribute("webkitallowfullscreen", "true");
		iframeFideo.getElement().setAttribute("allowfullscreen", "true");
						
	}

	public void addCloseClickHandler(ClickHandler closeClickHandler) {
		botwmCau.addClickHandler(closeClickHandler);
	}

	public void addRejectClickHandler(ClickHandler rejectClickHandler) {
		this.botwmGwrthod.addClickHandler(rejectClickHandler);
	}

}
