package pages;

import global.Page;
import controllers.routes;
import models.Right;
import play.api.mvc.Call;

public class AdmHuntEditPage extends Page {
	
	protected String hid = null;
	
	public AdmHuntEditPage(String title, Right accessRight, String startJS) throws Exception {
		super("huntedit", title, null, accessRight, startJS);
		menu.add("huntlist");
		menu.add(new AdmHuntEditPage(this));//Copy it
	}
	
	public AdmHuntEditPage(AdmHuntEditPage other) throws Exception {
		super(other);
	}
	
	public void setMyParameters(String hid) {
		this.hid = hid;
	}

	// The entry in the menu could (should) be a copy
	public void setMenuParameters(String hid) {
		AdmHuntEditPage p = (AdmHuntEditPage) menu.getPage(name);// Should be a copy of this one
		p.setMyParameters(hid);
	}
	
	@Override
	public Call getRoute() {
		return routes.AdminPanelController.huntedit(hid);
	}

	/*
	<ul class="breadcrumb">
		<li><a href="@routes.ManagerController.dashboard()">Tableau de bord</a></li>
		<li><a href="@routes.HuntController.show(step.getHunt().getId())"><span class="hunt-name">@step.getHunt().getLabel()</span></a></li>
		<li><a href="@routes.HuntController.show(step.getHunt().getId())">&Eacute;tape @step.getNumber()</a></li>
		<li><a>Nouvelle énigme</a></li>
	</ul>
	
	<ul class="breadcrumb">
		<li><a href="@routes.ManagerController.dashboard()">Tableau de bord</a></li>
		<li><a href="@routes.HuntController.show(step.getHunt().getId())"><span class="hunt-name">@step.getHunt().getLabel()</span></a></li>
		<li><a href="@routes.HuntController.show(step.getHunt().getId())">&Eacute;tape @step.getNumber()</a></li>
		<li><a>Nouvelle énigme</a></li>
	</ul>
	 */
}
