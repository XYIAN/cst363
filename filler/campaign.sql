drop table if exists campaign;

create table campaign
(
  cmte_id		varchar(12),
  cand_id		varchar(12),
  cand_nm		varchar(40),
  contbr_nm		varchar(40),
  contbr_city		varchar(40),
  contbr_st		varchar(40),
  contbr_zip		varchar(20),
  contbr_employer	varchar(60),
  contbr_occupation	varchar(40),
  contb_receipt_amt	numeric(6,2),
  contb_receipt_dt	varchar(20),
  receipt_desc		varchar(40),
  memo_cd		varchar(20),
  memo_text		varchar(20),
  form_tp               varchar(20),
  file_num		varchar(20),
  tran_id		varchar(20),
  election_tp		varchar(20)
);

.mode csv
.import campaign-2008-all.csv campaign

delete from campaign where rowid=1;

.separator ","
