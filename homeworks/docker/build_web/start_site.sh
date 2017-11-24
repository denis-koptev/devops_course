touch ip_file
echo $(hostname -I) | rev | cut -c 1- | rev | awk '{print $1":8000"}' > ip_file
python3 /django/test_site/manage.py runserver `cat ip_file`