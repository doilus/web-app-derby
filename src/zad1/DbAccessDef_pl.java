package zad1;

import java.util.ListResourceBundle;

public class DbAccessDef_pl extends ListResourceBundle{

	
	protected Object[][] getContents() {
		return contects;
	}
	
	static final Object[][] contects = {
			{"charset", "ISO-8859-2"},
			{"header", new String[] {"Baza danych książek"}},
			{"param_command", "Polecenie(select lub insert): "},
			{"submit", "Wykonaj"},
			{"footer", new String[] {} },
			{"resCode", new String[] {"Wynik: ", "Brak bazy", "Błąd SQL",
					"Wadliwe polecenie; musi zaczynać się od select lub insert"}
			},
			{"resDescr", new String[] { "" }},
	};
	
}
