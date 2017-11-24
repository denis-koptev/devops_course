from django.shortcuts import render
from main_page.models import User

def main(request):	

	# Query from db
	users = User.objects.all().order_by('id')

	context = {
		'students' : users
	} 

	return render(request, 'main_page/main_page.html', context)