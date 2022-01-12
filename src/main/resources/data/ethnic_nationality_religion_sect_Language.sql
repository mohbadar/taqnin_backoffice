INSERT INTO public.ethnic(
	id, active, created_by, deleted, name_en, name_dr, name_ps)
	VALUES 
	(1, 't', 'admin', 'f', 'pashtoon', 'پښتون', 'پښتون'),
	(2, 't', 'admin', 'f', 'tajik', 'تاجک', 'تاجک'),
	(3, 't', 'admin', 'f', 'hazara', 'هزاره', 'هزاره'),
	(4, 't', 'admin', 'f', 'uzbek', 'اوزبیک', 'اوزبیک'),
	(5, 't', 'admin', 'f', 'turkman', 'ترکمن', 'ترکمن'),
	(6, 't', 'admin', 'f', 'baloch', 'بلوچ', 'بلوچ'),
	(7, 't', 'admin', 'f', 'pashayi', 'پشه یی', 'پشه یی'),
	(8, 't', 'admin', 'f', 'aimaq', 'ایماق', 'ایماق'),
	(9, 't', 'admin', 'f', 'nooristani', 'نورستاني', 'نورستاني'),
	(10, 't', 'admin', 'f', 'arab', 'عرب', 'عرب'),
	(11, 't', 'admin', 'f', 'qarghaz', 'قرغیز', 'قرغیز'),
	(12, 't', 'admin', 'f', 'qazalbash', 'قزلباش', 'قزلباش'),
	(13, 't', 'admin', 'f', 'gujar', 'گوجر', 'گوجر'),
	(14, 't', 'admin', 'f', 'brahawi', 'براهوی', 'براهوی'),
	(15, 't', 'admin', 'f', 'ahl honod', 'اهل هنود', 'اهل هنود'),
	(16, 't', 'admin', 'f', 'sikh', 'سیکان', 'سیکان'),
	(17, 't', 'admin', 'f', 'sadat', 'سادات', 'سادات'),
	(18, 't', 'admin', 'f', 'foreigner', 'خارجي', 'خارجی'),
	(19, 't', 'admin', 'f', 'others', 'دیگر', 'دیگر');


    INSERT INTO public.nationality(
	id, active, created_by, deleted, name_en, name_dr, name_ps)
	VALUES 
	(1, 't', 'admin', 'f', 'afghan', 'افغان', 'افغان'),
	(2, 't', 'admin', 'f', 'other', 'دیگر', 'دیگر');


	INSERT INTO public.religion(
	id, active, created_by, deleted, name_en, name_dr, name_ps)
	VALUES 
	(1, 't', 'admin', 'f', 'islam', 'اسلام', 'اسلام'),
	(2, 't', 'admin', 'f', 'Hindu', 'هندو', 'هندو'),
	(3, 't', 'admin', 'f', 'Sikh', 'سیک', 'سیک'),
	(4, 't', 'admin', 'f', 'Christian', 'عسیوی', 'عسیوی'),
	(5, 't', 'admin', 'f', 'Judaism', 'یهودی', 'یهودی'),
	(6, 't', 'admin', 'f', 'Buddhism', 'بودایی', 'بودایی');

	INSERT INTO public.sect(
	id, active, created_by, deleted, name_en, name_dr, name_ps)
	VALUES 
	(1, 't', 'admin', 'f', 'Suni', 'سنی', 'سنی'),
	(2, 't', 'admin', 'f', 'Shia', 'شیعه', 'شیعه'),
	(3, 't', 'admin', 'f', 'Other', 'دیگر', 'دیگر');


	INSERT INTO public.language(
	id, active, created_by, deleted, name_en, name_dr, name_ps)
	VALUES 
	(1, 't', 'admin', 'f', 'English', 'انگلیسی', 'انگلیسی'),
	(2, 't', 'admin', 'f', 'Spanish', 'اسپانیایی', 'اسپانیایی'),
	(3, 't', 'admin', 'f', 'ًRussian', 'روسی', 'روسی'),
	(4, 't', 'admin', 'f', 'Franch', 'فرانسوی', 'فرانسوی'),
	(5, 't', 'admin', 'f', 'Other foreign language', 'سایر زبان های خارجی', 'سایر زبان های خارجی');

	INSERT INTO public.national_language(
	id, active, created_by, deleted, name_dr, name_en, name_ps)
	VALUES 
	(1, 't', 'admin', 'f', 'پشتو','Pashto','پښتو'),
	(2, 't', 'admin', 'f', 'دری','Dari','دری'),
	(3, 't', 'admin', 'f', 'اوزبیکی','Uzbek','اوزبیکی'),
	(4, 't', 'admin', 'f', 'ترکمنی','Turkmen','ترکمني'),
	(5, 't', 'admin', 'f', 'بلوچی','Balochi','بلوچي'),
	(6, 't', 'admin', 'f', 'پشه ای','Pashaee','پشه یی'),
	(7, 't', 'admin', 'f', 'نورستانی','Nooristani','نورستانی'),
	(8, 't', 'admin', 'f', 'پامیری','Pamiri','پامیری'),
	(9, 't', 'admin', 'f', 'اورمری','Urmaly','اورمري'),
	(10, 't', 'admin', 'f', 'پراچی','Prachi','پراچي'),
	(11, 't', 'admin', 'f', 'منجی','Manji','منجی'),
	(12, 't', 'admin', 'f', 'سنگلیچی-اشکاشمی','Singlechi-Ishkashmi','سنگلیچی-اشکاشمی'),
	(13, 't', 'admin', 'f', 'شغنی-روشانی','Shaghni-Rohani','شغنی - روحانی'),
	(14, 't', 'admin', 'f', 'واخی','Wakhi','واخی'),
	(15, 't', 'admin', 'f', 'کاتی-باشگلی','Katie-Bashgly','کاتی-باشگلی'),
	(16, 't', 'admin', 'f', 'وایگلی-تریگامی','Wigley-Trigami','ویګلی - ټریګامي'),
	(17, 't', 'admin', 'f', 'اشکون-وامایی','Ashkoon-Wamai','اشکون - وامی'),
	(18, 't', 'admin', 'f', 'پرسونی-واسی','Personi-Wasi','پرسوني - واسي'),
	(19, 't', 'admin', 'f', 'گوریاتی','Goryati','ګوریاتی'),
	(20, 't', 'admin', 'f', 'ننگلامی','Nanglami','ننگلامی'),
	(21, 't', 'admin', 'f', 'وته پوري-کتارکلایی', 'Watapori-Katarkalayee', 'وټه پوري-کتارکلایی'),
	(22, 't', 'admin', 'f', 'ساوی', 'Savi', 'ساوی'),
	(23, 't', 'admin', 'f', 'تیراهی', 'Terahi', 'تیراهی'),
	(24, 't', 'admin', 'f', 'هندکو', 'Hindko', 'هندکو'),
	(25, 't', 'admin', 'f', 'گوجری', 'Gujari', 'گوجری'),
	(26, 't', 'admin', 'f', 'پنجاپی-اردو', 'Punjabi-Urdu', 'پنجاپی-اردو'),
	(27, 't', 'admin', 'f', 'سندهی', 'Sindi', 'سندهی'),
	(28, 't', 'admin', 'f', 'ملتانی', 'Multani', 'ملتانی'),
	(29, 't', 'admin', 'f', 'مغولی', 'Mongol', 'مغولی'),
	(30, 't', 'admin', 'f', 'قرغزی', 'Kyrgyz', 'قرغزی'),
	(31, 't', 'admin', 'f', 'قزاقی', 'Qazaqi', 'قزاقی'),
	(32, 't', 'admin', 'f', 'افشاری', 'Afshari', 'افشاری'),
	(33, 't', 'admin', 'f', 'براهویی', 'Brahui', 'براهویی'),
	(34, 't', 'admin', 'f', 'عربی', 'Arabic', 'عربی');


	INSERT INTO public.employee_grade(
	id, active, created_by, deleted, name_en, name_dr, name_ps)
	VALUES 
	(1, 't', 'admin', 'f', 'Above Grade', ' مافوق رتبه', 'مافوق رتبه'),
	(2, 't', 'admin', 'f', 'Outside Grade', ' فوق رتبه', 'فوق رتبه'),
	(3, 't', 'admin', 'f', 'One', ' رتبه اول', 'رتبه اول'),
	(4, 't', 'admin', 'f', 'ُTwo', 'رتبه دوم', 'رتبه دوم'),
	(5, 't', 'admin', 'f', 'ُThree', ' رتبه سوم', 'رتبه سوم'),
	(6, 't', 'admin', 'f', 'Fourth', 'رتبه چهارم', 'رتبه چهارم'),
	(7, 't', 'admin', 'f', 'Fifth', ' رتبه پنجم', 'رتبه پنجم'),
	(8, 't', 'admin', 'f', 'Sixth', 'رتبه ششم', 'رتبه ششم'),
	(9, 't', 'admin', 'f', 'Seventh', 'رتبه هفتم', 'رتبه هفتم'),
	(10,'t', 'admin', 'f', 'Eight', 'رتبه هشتم', 'رتبه هشتم'),
	(11, 't', 'admin', 'f', 'Ninth', 'رتبه نهم', 'رتبه نهم'),
	(12,'t', 'admin', 'f', 'Tenth', 'رتبه دهم', 'رتبه دهم');

	INSERT INTO public.employee_position(
	id, active, created_by, deleted, name_en, name_dr, name_ps)
	VALUES 
	(1, 't', 'admin', 'f', 'Outside Grade', 'خارج رتبه', 'خارج رتبه'),
	(2, 't', 'admin', 'f', 'Above Grade', 'مافوق رتبه', 'مافوق رتبه'),
	(3, 't', 'admin', 'f', 'Grade', ' فوق رتبه', 'فوق رتبه'),
	(4, 't', 'admin', 'f', 'One', ' رتبه اول', 'رتبه اول'),
	(5, 't', 'admin', 'f', 'ُTwo', 'رتبه دوم', 'رتبه دوم'),
	(6, 't', 'admin', 'f', 'ُThree', ' رتبه سوم', 'رتبه سوم'),
	(7, 't', 'admin', 'f', 'Fourth', 'رتبه چهارم', 'رتبه چهارم'),
	(8, 't', 'admin', 'f', 'Fifth', ' رتبه پنجم', 'رتبه پنجم'),
	(9, 't', 'admin', 'f', 'Sixth', 'رتبه ششم', 'رتبه ششم'),
	(10, 't', 'admin', 'f', 'Seventh', 'رتبه هفتم', 'رتبه هفتم'),
	(11,'t', 'admin', 'f', 'Eight', 'رتبه هشتم', 'رتبه هشتم'),
	(12, 't', 'admin', 'f', 'Ninth', 'رتبه نهم', 'رتبه نهم'),
	(13,'t', 'admin', 'f', 'Tenth', 'رتبه دهم', 'رتبه دهم');

	INSERT INTO public.employee_status(
	id, active, created_by, deleted, name_en, name_dr, name_ps)
	VALUES 
	(1, 't', 'admin', 'f', 'Active', 'برحال', 'برحال'),
	(2, 't', 'admin', 'f', 'Change', 'تبدیل', 'تبدیل'),
	(3, 't', 'admin', 'f', 'Waiting With Salary', 'انتظار با معاش', 'انتظار به معاش'),
	(4, 't', 'admin', 'f', 'Waiting without Salary', 'انتظار بدون معاش', 'انتظار بدون معاش'),
	(5, 't', 'admin', 'f', 'Home Stay', 'انفصال', 'انفصال'),
	(6, 't', 'admin', 'f', 'ًًRetired', 'متقاعد', 'متقاعد'),
	(7, 't', 'admin', 'f', 'Death', 'فوتی', 'فوتی'),
	(8, 't', 'admin', 'f', 'suspended', 'تعلیق', 'تعلیق');