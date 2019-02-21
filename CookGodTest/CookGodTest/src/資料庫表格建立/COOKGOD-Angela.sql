--------------------------------------------------------
--  File created - Tuesday-February-19-2019   
--------------------------------------------------------
REM INSERTING into FEST_MENU
SET DEFINE OFF;
Insert into FEST_MENU (FEST_M_ID,FEST_M_NAME,FEST_M_QTY,FEST_M_START,FEST_M_END,FEST_M_SEND,FEST_M_STATUS,FEST_M_KIND,CHEF_ID) values ('FM0002','節慶主題料理名稱一',50,to_date('01-JAN-19','DD-MON-RR'),to_date('01-FEB-19','DD-MON-RR'),to_date('14-FEB-19','DD-MON-RR'),'1','1','C00002');
Insert into FEST_MENU (FEST_M_ID,FEST_M_NAME,FEST_M_QTY,FEST_M_START,FEST_M_END,FEST_M_SEND,FEST_M_STATUS,FEST_M_KIND,CHEF_ID) values ('FM0003','節慶主題料理名稱二',20,to_date('18-FEB-19','DD-MON-RR'),to_date('01-FEB-19','DD-MON-RR'),to_date('14-FEB-19','DD-MON-RR'),'1','1','C00008');
Insert into FEST_MENU (FEST_M_ID,FEST_M_NAME,FEST_M_QTY,FEST_M_START,FEST_M_END,FEST_M_SEND,FEST_M_STATUS,FEST_M_KIND,CHEF_ID) values ('FM0004','節慶主題料理名稱三',50,to_date('05-FEB-19','DD-MON-RR'),to_date('01-FEB-19','DD-MON-RR'),to_date('14-FEB-19','DD-MON-RR'),'1','1','C00008');

--------------------------------------------------------
--  File created - Tuesday-February-19-2019   
--------------------------------------------------------
REM INSERTING into FEST_DISH
SET DEFINE OFF;
Insert into FEST_DISH (DISH_ID,FEST_M_ID) values ('D00001','FM0003');
Insert into FEST_DISH (DISH_ID,FEST_M_ID) values ('D00005','FM0003');
Insert into FEST_DISH (DISH_ID,FEST_M_ID) values ('D00002','FM0003');
Insert into FEST_DISH (DISH_ID,FEST_M_ID) values ('D00004','FM0003');


--------------------------------------------------------
--  File created - Tuesday-February-19-2019   
--------------------------------------------------------
REM INSERTING into FEST_ORDER
SET DEFINE OFF;
Insert into FEST_ORDER (FEST_OR_ID,FEST_OR_STATUS,FEST_OR_PRICE,FEST_OR_START,FEST_OR_SEND,FEST_OR_END,FEST_OR_DISC,CUST_ID) values ('FM20190219-000003','3',3000,to_date('01-FEB-19','DD-MON-RR'),to_date('14-FEB-19','DD-MON-RR'),to_date('01-FEB-19','DD-MON-RR'),'0.8','C00001');
Insert into FEST_ORDER (FEST_OR_ID,FEST_OR_STATUS,FEST_OR_PRICE,FEST_OR_START,FEST_OR_SEND,FEST_OR_END,FEST_OR_DISC,CUST_ID) values ('FM20190219-000001','4',2800,to_date('18-FEB-19','DD-MON-RR'),to_date('18-FEB-19','DD-MON-RR'),to_date('18-FEB-19','DD-MON-RR'),'0.8','C00001');
Insert into FEST_ORDER (FEST_OR_ID,FEST_OR_STATUS,FEST_OR_PRICE,FEST_OR_START,FEST_OR_SEND,FEST_OR_END,FEST_OR_DISC,CUST_ID) values ('FM20190219-000002','4',2800,to_date('18-FEB-19','DD-MON-RR'),to_date('18-FEB-19','DD-MON-RR'),to_date('18-FEB-19','DD-MON-RR'),'0.8','C00001');

--------------------------------------------------------
--  File created - Tuesday-February-19-2019   
--------------------------------------------------------
REM INSERTING into FEST_ORDER_DETAIL
SET DEFINE OFF;
Insert into FEST_ORDER_DETAIL (FEST_OR_ID,FEST_M_ID,FEST_OR_RATE,FEST_OR_MSG,FEST_OR_QTY,FEST_OR_STOTAL) values ('FM20190219-000003','FM0003',2,'很不錯喔, 下次仍會繼續購買',100,120);
Insert into FEST_ORDER_DETAIL (FEST_OR_ID,FEST_M_ID,FEST_OR_RATE,FEST_OR_MSG,FEST_OR_QTY,FEST_OR_STOTAL) values ('FM20190219-000001','FM0003',1,'很不錯喔, 下次仍會繼續購買',50,80);

--------------------------------------------------------
--  File created - Tuesday-February-19-2019   
--------------------------------------------------------
REM INSERTING into REPORT
SET DEFINE OFF;
Insert into REPORT (REPORT_ID,REPORT_TITLE,REPORT_SORT,REPORT_START,REPORT_STATUS,REPORT_CON,CUST_ID,FORUM_ART_ID) values ('R00001','檢舉主廚專區文章','1',to_timestamp('01-FEB-19 04.58.25.173000000 AM','DD-MON-RR HH.MI.SSXFF AM'),'1','檢舉內容一','C00001','A00001');
Insert into REPORT (REPORT_ID,REPORT_TITLE,REPORT_SORT,REPORT_START,REPORT_STATUS,REPORT_CON,CUST_ID,FORUM_ART_ID) values ('R00002','檢舉會員專區文章','2',to_timestamp('08-FEB-19 05.02.39.743000000 AM','DD-MON-RR HH.MI.SSXFF AM'),'1','檢舉內容二','C00001','A00001');
Insert into REPORT (REPORT_ID,REPORT_TITLE,REPORT_SORT,REPORT_START,REPORT_STATUS,REPORT_CON,CUST_ID,FORUM_ART_ID) values ('R00003','檢舉主廚專區文章','1',to_timestamp('07-FEB-19 05.04.12.269000000 AM','DD-MON-RR HH.MI.SSXFF AM'),'1','檢舉內容三','C00003','A00001');

