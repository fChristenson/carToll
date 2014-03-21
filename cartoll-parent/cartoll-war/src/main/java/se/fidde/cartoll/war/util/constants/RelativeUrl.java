package se.fidde.cartoll.war.util.constants;

public enum RelativeUrl {
	PROPERTIES("/properties/"), ENGLISH_LANGUAGE("/properties/en_US.properties"), HEADER("/WEB-INF/jsp/header.jsp"), SHOW_STATION(
			"showStation.html?id="), ADD_PASSING("addNewPassing.html"), REMOVE_ALL_PASSINGS("removeAllPassings.html"), CSS(
			"/main.css"), OWNER("owner.html?id="), INDEX_HTML("index.html");

	private final String URL;

	private RelativeUrl(String url) {
		URL = url;
	}

	@Override
	public String toString() {
		return URL;
	}
}
