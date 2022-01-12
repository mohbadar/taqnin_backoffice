
CREATE FUNCTION update_profile() RETURNS trigger AS $$
DECLARE
    latest_profile_job_id integer := 0;
    lst_profile_id integer := 0;
    lst_employee_position integer := 0;
    lst_military_position integer := 0;
    lst_position_title varchar := '';
    lst_ministry integer := 0;
    lst_authority integer := 0;
    lst_commission integer := 0;
BEGIN

    select profile_id, employee_position, position_title, ministry, authority, commission, military_position into lst_profile_id, lst_employee_position, lst_position_title, lst_ministry, lst_authority, lst_commission, lst_military_position from public.profile_job where profile_id = NEW.profile_id and deleted is not True and accountability_id is null and job_break is null order by maktub_date desc limit 1;
    
    UPDATE public.profile SET employee_position=lst_employee_position, position_title=lst_position_title, first_ministry=lst_ministry, first_authority=lst_authority, first_commission=lst_commission, military_position=lst_military_position where id=lst_profile_id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
-- end of first fuction


CREATE FUNCTION update_profile_byPromotion() RETURNS trigger AS $$
DECLARE
    lst_profile_id integer := 0;
    lst_employee_grade integer := 0;
    lst_employee_military_grade integer := 0;

BEGIN

    select profile_id, new_grade, new_military_grade into lst_profile_id, lst_employee_grade, lst_employee_military_grade from public.profile_promotion where profile_id = NEW.profile_id and deleted is not True order by maktub_date desc limit 1;
    
    UPDATE public.profile SET employee_grade=lst_employee_grade, military_grade =lst_employee_military_grade, disable=True where id=lst_profile_id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


-- Triggers

CREATE TRIGGER tr_update_profile_job
AFTER INSERT OR UPDATE ON public.profile_job FOR EACH ROW EXECUTE PROCEDURE update_profile();




CREATE TRIGGER tr_update_profile_promotion
AFTER INSERT OR UPDATE ON public.profile_promotion FOR EACH ROW EXECUTE PROCEDURE update_profile_byPromotion();


