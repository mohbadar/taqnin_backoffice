INSERT INTO public.profile_retirement_type(
	id, active, created_by, deleted, name_dr, name_en, name_ps)
	VALUES 
	(1, 't', 'admin', 'f', 'تقاعد به اساس کبر سن','Retirement based on age','د عمر پر اساس تقاعد'),
	(2, 't', 'admin', 'f', 'تقاعد به اساس خدمت بالفعل','A pension with a foundation already served','یو تقاعد چې دمخه یې خدمت کړی و'),
	(3, 't', 'admin', 'f', 'تقاعد داوطلبانه ','Voluntary retirement','په خپله خوښه تقاعد'),
	(4, 't', 'admin', 'f', 'تقاعد مریضی ناشی از کار ','Retirement from work-related illness','د کار پورې اړوند ناروغۍ څخه تقاعد'),
	(5, 't', 'admin', 'f', 'تقاعد فوتی ناشی از کار ','Work-induced retirement','له کار څخه د مرګ هڅول تقاعد'),
	(6, 't', 'admin', 'f', 'تقاعد اجباری','Compulsory retirement','اجباري تقاعد'),
	(7, 't', 'admin', 'f', 'تقاعد ناشی از مریضی غیر مرتبط به کار','Retirement due to an unrelated illness','د نه تړلې ناروغۍ له امله تقاعد'),
	(8, 't', 'admin', 'f', 'تقاعد ناشی از فوت غیر مرتبط به کار ','Retirement resulting from death unrelated to work','تقاعد د مړینې له امله چې کار پورې اړوند نه وي'),
	(9, 't', 'admin', 'f', 'تقاعد معلولین ','Retirement of the disabled','د معلولینو تقاعد'),
	(10, 't', 'admin', 'f', 'تقاعد شهدا و مفقودین ','Retirement of the martyrs and the missing','د شهیدانو او ورک کیدلو تقاعد'),
	(11, 't', 'admin', 'f', 'ترفیعاً تقاعد ','A retirement increase','د تقاعد زیاتوالی');