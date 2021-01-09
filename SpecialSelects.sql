-- SELECT V SELECTU
  -- Vyber jméno stanice a počet testů, které na ní byly provedeny
  SELECT S.st_name, pocetTestu = (SELECT COUNT(R.station_id)
                                  FROM Results R
                                  WHERE R.station_id = S.st_id)
  FROM Stations S ORDER BY pocetTestu DESC
-- vnořený SELECT ve FROM
  -- vyber pacienty, kteří byly testování více než jednou (jméno, pøíjmení, email, telefon) a kolikrát
  SELECT P.[name],P.surname,P.email,P.phone, X.pocet FROM Patients P,
  (SELECT R.patient_id, COUNT(R.patient_id) AS pocet FROM Results R GROUP BY R.patient_id HAVING COUNT(R.patient_id) > 1) X
  WHERE P.p_id = X.patient_id

-- vnořený SELECT ve WHERE
  -- vyber pacienty, kteøí ještì nebyly testovaní
  SELECT P.[name],P.surname, P.email,P.phone FROM Patients P WHERE P.p_id NOT IN (SELECT R.patient_id FROM Results R)

-- GROUP BY
  -- Vypiš všechny stanice seskupené podle krajů
  SELECT C.county_name,S.st_name FROM Stations S, Counties C GROUP BY C.county_name, S.st_name 
  
-- množinová operace
  -- Vyber uživatele, který byl testován
  SELECT P.[name],P.surname,P.email,P.phone FROM Patients P, (SELECT P.p_id FROM Patients P
									INTERSECT
									SELECT R.patient_id FROM Results R ) X
  WHERE X.p_id = P.p_id
-- LEFT JOIN
  -- Vyber všechny Testy - jméno testu a počet kolikrát byl proveden a jeho výsledek byl "Pozitivní"
  SELECT T.test_id,T.test_name,COUNT(R.test_id) AS pocet FROM Tests T LEFT JOIN Results R ON R.test_id = T.test_id WHERE R.status = 'Pozitivní' GROUP BY R.test_id,T.test_name,T.test_id

