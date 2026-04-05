select 7 as month,count(distinct user_id) as monthly_active_users
from user_actions where date_trunc('month', event_date) = '2022-07-01'and user_id in (
select user_id from user_actions where date_trunc('month', event_date) = '2022-06-01');
