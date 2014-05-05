package pl.com.softproject.utils.payu.model;

public enum ErrorMessage
{
  M100("100", "brak parametru pos id"),  M101("101", "brak parametru session id"),  M102("102", "brak parametru ts"),  M103("103", "brak parametru sig"),  M104("104", "brak parametru desc"),  M105("105", "brak parametru client ip"),  M106("106", "brak parametru first name"),  M107("107", "brak parametru last name"),  M108("108", "brak parametru street"),  M109("109", "brak parametru city"),  M110("110", "brak parametru post code"),  M111("111", "brak parametru amount"),  M112("112", "błędny numer konta bankowego"),  M113("200", "inny chwilowy błšd"),  M201("201", "inny chwilowy błšd bazy danych"),  M202("202", "POS o podanym identyfikatorze jest zablokowany"),  M203("203", "niedozwolona wartoć pay_type dla danego pos_id"),  M204("204", "podana metoda płatnosci (wartoć pay_type) jest chwilowo zablokowana dla danego pos_id, np. przerwa konserwacyjna bramki płatniczej"),  M205("205", "kwota transakcji mniejsza od wartoci minimalnej"),  M206("206", "kwota transakcji większa od wartoci maksymalnej"),  M207("207", "przekroczona wartoć wszystkich transakcji dla jednego klienta w ostatnim przedziale czasowym"),  M208("208", "POS działa w wariancie ExpressPayment lecz nie nastapiła aktywacja tego wariantu współpracy (czekamy na zgode działu obsługi klienta)"),  M209("209", "błedny numer pos_id lub pos_auth_key"),  M500("500", "transakcja nie istnieje"),  M501("501", "brak autoryzacji dla danej transakcji"),  M502("502", "transakcja rozpoczęta wcześniej"),  M503("503", "autoryzacja do transakcji była już przeprowadzana"),  M504("504", "transakcja anulowana wczeniej"),  M505("505", "transakcja przekazana do odbioru wcześniej"),  M506("506", "transakcja już odebrana"),  M507("507", "błąd podczas zwrotu  środków do klienta"),  M599("599", "błędny stan transakcji, np. nie można uznać transakcji kilka razy lub inny, prosimy o kontakt"),  M999("999", "inny błąd krytyczny - prosimy o kontakt");
  
  private String code;
  private String message;
  
  private ErrorMessage(String code, String message)
  {
    this.code = code;
    this.message = message;
  }
  
  public String getCode()
  {
    return this.code;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public static ErrorMessage getByCode(String code)
  {
    return valueOf("M" + code);
  }
  
  public String toString()
  {
    return "ErrorMessage{code=" + this.code + ", message=" + this.message + '}';
  }
}
