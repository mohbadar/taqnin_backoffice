insert into public.profile(id, age, description, email, first_name, surname,
						   salary, status, created_at, 
						   deleted_at, deleted_by, updated_at)
select n, n, 'Test Data', 'test@gmail.com', 'Ahmad', 'Khan',
		n*1000, false, now() - random() * INTERVAL '2 days',
		null, null, now() - random() * INTERVAL '3 days'
from generate_series (1, 100) n;



insert into public.retirement(id, created_at, deleted_at, deleted_by, status, updated_at)
select n,
		now() - random() * INTERVAL '2 days',
		null,
		null,
		false, 
       now() - random() * INTERVAL '3 days'
from generate_series (1, 100) n;


