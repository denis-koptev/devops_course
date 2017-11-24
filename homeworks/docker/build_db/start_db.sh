service mysql start
mysql --user=root --execute "create database test_site_db"
mysql --user=root test_site_db < test_site_db.sql
mysql --user=root --execute "create user test_user@localhost identified by 'user'"
mysql --user=root --execute "grant all privileges on test_site_db.* to test_user@localhost"
echo DB started