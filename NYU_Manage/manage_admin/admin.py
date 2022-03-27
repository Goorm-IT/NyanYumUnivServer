from django.contrib import admin
from .models import *

# Register your models here.


class CommentAdmin(admin.ModelAdmin):
    search_fields = ['commentid']


admin.site.register(Comment, CommentAdmin)


# class MenuAdmin(admin.ModelAdmin):
#     search_fields = ['storeid']


# admin.site.register(Menu, MenuAdmin)


class StoreAdmin(admin.ModelAdmin):
    search_fields = ['storeid']


admin.site.register(Store, StoreAdmin)


class UserAdmin(admin.ModelAdmin):
    search_fields = ['uid']


admin.site.register(User, UserAdmin)
