update user
set money  = money - /*user.money*/100 , historymoney = historymoney + /*user.historymoney*/100
where name = 'ゲスト';