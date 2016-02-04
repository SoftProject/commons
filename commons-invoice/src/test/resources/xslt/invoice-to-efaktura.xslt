<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:java="http://xml.apache.org/xslt/java"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns2="http://www.softproject.com.pl/commons/model/invoice"
                version="2.0" exclude-result-prefixes="java">
    <xsl:output method="xml" version="1.0" encoding="utf-8" indent="yes"/>
    <xsl:template match="/">
        <FormatXML>
            <e-Faktura>
                <Wersja>1</Wersja>
            </e-Faktura>
            <DokumentHandlowy>
                <DataWplywu/>
                <GUID/>
                <Status>Faktura</Status>
                <DataWystawieniaDuplikatu/>
                <NumerDokumentu>
                    <xsl:value-of select="/ns2:invoice/invoice-number"/>
                </NumerDokumentu>
                <Nazwa/>
                <Charakter/>
                <Typ/>
                <Seria/>
                <Wystawca>
                    <GUIDEDS/>
                    <GUID/>
                    <Kod/>
                    <Nazwa>
                        <xsl:value-of select="/ns2:invoice/seller/seller-name"/>
                    </Nazwa>
                    <Ulica>
                        <xsl:value-of select="/ns2:invoice/seller/seller-street"/>
                    </Ulica>
                    <NumerDomu>
                        <xsl:value-of select="/ns2:invoice/seller/seller-house-number"/>
                    </NumerDomu>
                    <NumerLokalu/>
                    <KodPocztowy>
                        <xsl:value-of select="/ns2:invoice/seller/seller-post-code"/>
                    </KodPocztowy>
                    <Miejscowosc>
                        <xsl:value-of select="/ns2:invoice/seller/seller-city"/>
                    </Miejscowosc>
                    <Wojewodztwo/>
                    <Kraj/>
                    <NIP>
                        <xsl:value-of select="/ns2:invoice/seller/seller-taxId"/>
                    </NIP>
                    <REGON/>
                    <PESEL/>
                    <VIES/>
                    <VATUE/>
                    <NumerRachunkuBankowego>
                        <xsl:value-of select="/ns2:invoice/seller/seller-account"/>
                    </NumerRachunkuBankowego>
                    <Telefon1/>
                    <Telefon2/>
                    <Fax/>
                    <Email/>
                </Wystawca>
                <Odbiorca>
                    <GUIDEDS/>
                    <GUID/>
                    <Kod/>
                    <Nazwa>
                        <xsl:value-of select="/ns2:invoice/buyer/buyer-name"/>
                    </Nazwa>
                    <Ulica>
                        <xsl:value-of select="/ns2:invoice/buyer/buyer-street"/>
                    </Ulica>
                    <NumerDomu>
                        <xsl:value-of select="/ns2:invoice/buyer/buyer-house-number"/>
                    </NumerDomu>
                    <NumerLokalu/>
                    <KodPocztowy>
                        <xsl:value-of select="/ns2:invoice/buyer/buyer-post-code"/>
                    </KodPocztowy>
                    <Miejscowosc>
                        <xsl:value-of select="/ns2:invoice/buyer/buyer-city"/>
                    </Miejscowosc>
                    <Wojewodztwo/>
                    <Kraj/>
                    <NIP>
                        <xsl:value-of select="/ns2:invoice/buyer/buyer-taxId"/>
                    </NIP>
                    <REGON/>
                    <PESEL/>
                    <VIES/>
                    <VATUE/>
                    <NumerRachunkuBankowego>
                        <xsl:value-of select="/ns2:invoice/buyer/buyer-account"/>
                    </NumerRachunkuBankowego>
                    <Telefon1/>
                    <Telefon2/>
                    <Fax/>
                    <Email/>
                </Odbiorca>
                <Rejestr/>
                <RodzajCeny/>
                <NaliczanieVAT/>
                <MetodaRozliczaniaVAT/>
                <DataWystawienia>
                    <xsl:value-of select="substring(/ns2:invoice/issue-date, 0, 11)"/>
                </DataWystawienia>
                <DataSprzedazy>
                    <xsl:value-of select="substring(/ns2:invoice/sell-date, 0, 11)"/>
                </DataSprzedazy>
                <DataWplywu/>
                <RejestrPlatnosci/>
                <FormaPlatnosci/>
                <TerminPlatnosci>
                    <xsl:value-of select="concat(/ns2:invoice/summary/days-to-pay, ' dni')"/>
                </TerminPlatnosci>
                <DataPlatnosci>
                    <xsl:value-of select="substring(/ns2:invoice/summary/payment-date, 0, 11)"/>
                </DataPlatnosci>
                <Waluta>
                    <Kod/>
                    <Nazwa/>
                    <Przelicznik/>
                </Waluta>
                <KursVAT>1.0</KursVAT>
                <KursCITPIT/>
                <OdebranyPrzez>
                    <xsl:value-of select="/ns2:invoice/summary/signer"/>
                </OdebranyPrzez>
                <StawkaVAT>
                    <Kod>23%</Kod>
                    <Nazwa>23%</Nazwa>
                    <Wartosc>0.23</Wartosc>
                    <ObowiazujeOd/>
                    <ObowiazujeDo/>
                    <StawkaRR/>
                    <Typ/>
                </StawkaVAT>
                <StawkaVAT>
                    <Kod>8%</Kod>
                    <Nazwa>8%</Nazwa>
                    <Wartosc>0.08</Wartosc>
                    <ObowiazujeOd/>
                    <ObowiazujeDo/>
                    <StawkaRR/>
                    <Typ/>
                </StawkaVAT>
                <StawkaVAT>
                    <Kod>7%</Kod>
                    <Nazwa>7%</Nazwa>
                    <Wartosc>0.07</Wartosc>
                    <ObowiazujeOd/>
                    <ObowiazujeDo/>
                    <StawkaRR/>
                    <Typ/>
                </StawkaVAT>
                <StawkaVAT>
                    <Kod>4%</Kod>
                    <Nazwa>4%</Nazwa>
                    <Wartosc>0.04</Wartosc>
                    <ObowiazujeOd/>
                    <ObowiazujeDo/>
                    <StawkaRR/>
                    <Typ/>
                </StawkaVAT>
                <StawkaVAT>
                    <Kod>NP</Kod>
                    <Nazwa>NP</Nazwa>
                    <Wartosc/>
                    <ObowiazujeOd/>
                    <ObowiazujeDo/>
                    <StawkaRR/>
                    <Typ/>
                </StawkaVAT>
                <StawkaVAT>
                    <Kod>ZW</Kod>
                    <Nazwa>ZW</Nazwa>
                    <Wartosc/>
                    <ObowiazujeOd/>
                    <ObowiazujeDo/>
                    <StawkaRR/>
                    <Typ/>
                </StawkaVAT>
                <Stopka>
                    <KwotaVAT>
                        <StawkaVAT>23%</StawkaVAT>
                        <Netto>
                            <xsl:value-of select="/ns2:invoice/summary/tax-summary/tax-23/netto"/>
                        </Netto>
                        <VAT>
                            <xsl:value-of select="/ns2:invoice/summary/tax-summary/tax-23/tax"/>
                        </VAT>
                    </KwotaVAT>
                    <KwotaVAT>
                        <StawkaVAT>8%</StawkaVAT>
                        <Netto>
                            <xsl:value-of select="/ns2:invoice/summary/tax-summary/tax-8/netto"/>
                        </Netto>
                        <VAT>
                            <xsl:value-of select="/ns2:invoice/summary/tax-summary/tax-8/tax"/>
                        </VAT>
                    </KwotaVAT>
                    <KwotaVAT>
                        <StawkaVAT>7%</StawkaVAT>
                        <Netto>
                            <xsl:value-of select="/ns2:invoice/summary/tax-summary/tax-7/netto"/>
                        </Netto>
                        <VAT>
                            <xsl:value-of select="/ns2:invoice/summary/tax-summary/tax-7/tax"/>
                        </VAT>
                    </KwotaVAT>
                    <KwotaVAT>
                        <StawkaVAT>4%</StawkaVAT>
                        <Netto>
                            <xsl:value-of select="/ns2:invoice/summary/tax-summary/tax-4/netto"/>
                        </Netto>
                        <VAT>
                            <xsl:value-of select="/ns2:invoice/summary/tax-summary/tax-4/tax"/>
                        </VAT>
                    </KwotaVAT>
                    <KwotaVAT>
                        <StawkaVAT>NP</StawkaVAT>
                        <Netto>
                            <xsl:value-of select="/ns2:invoice/summary/tax-summary/tax-np/netto"/>
                        </Netto>
                        <VAT>
                            <xsl:value-of select="/ns2:invoice/summary/tax-summary/tax-np/tax"/>
                        </VAT>
                    </KwotaVAT>
                    <KwotaVAT>
                        <StawkaVAT>ZW</StawkaVAT>
                        <Netto>
                            <xsl:value-of select="/ns2:invoice/summary/tax-summary/tax-zw/netto"/>
                        </Netto>
                        <VAT>
                            <xsl:value-of select="/ns2:invoice/summary/tax-summary/tax-zw/tax"/>
                        </VAT>
                    </KwotaVAT>
                </Stopka>

                <xsl:for-each select="/ns2:invoice/items">
                    <PozycjaDokumentu>
                        <Lp>
                            <xsl:value-of select="item/no"/>
                        </Lp>
                        <Towar>
                            <Kod>
                                <xsl:value-of select="item/code"/>
                            </Kod>
                            <Nazwa>
                                <Opis>
                                    <xsl:value-of select="item/name"/>
                                </Opis>
                            </Nazwa>
                            <KodPaskowy/>
                            <PKWiU/>
                            <CN/>
                            <JednostkaMiary>
                                <Ewidencyjna/>
                                <Dodatkowa1/>
                                <Przelicznik1/>
                                <Dodatkowa2/>
                                <Przelicznik2/>
                            </JednostkaMiary>
                            <StawkaVAT>
                                <xsl:value-of select="item/tax-type"/>
                            </StawkaVAT>
                            <Rodzaj/>
                        </Towar>
                        <JednostkaMiary>
                            <xsl:value-of select="item/unit-name"/>
                        </JednostkaMiary>
                        <Ilosc>
                            <xsl:value-of select="item/qty"/>
                        </Ilosc>
                        <Cena>
                            <xsl:value-of select="item/net-value"/>
                        </Cena>
                        <StawkaVAT>
                            <xsl:value-of select="item/tax-type"/>
                        </StawkaVAT>
                        <Wartosc>
                            <xsl:value-of select="item/net-amount"/>
                        </Wartosc>
                        <WartoscVAT>
                            <xsl:value-of select="item/vat-amount"/>
                        </WartoscVAT>
                    </PozycjaDokumentu>
                </xsl:for-each>
            </DokumentHandlowy>
        </FormatXML>
    </xsl:template>
</xsl:stylesheet>
