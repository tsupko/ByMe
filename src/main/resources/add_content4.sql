ALTER TABLE AD ADD COLUMN "date" DATE;
ALTER TABLE AD ADD COLUMN "day_count" int;

UPDATE ad set date = to_date('2019-02-28', 'yyyy-mm-dd');
UPDATE ad set day_count = 10;