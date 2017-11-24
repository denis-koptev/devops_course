from django.conf.urls import url, include
from django.contrib import admin
import main_page.views as main_views


urlpatterns = [
    url(r'^$', main_views.main, name='main_page'),
    url(r'^admin/', admin.site.urls),
]
