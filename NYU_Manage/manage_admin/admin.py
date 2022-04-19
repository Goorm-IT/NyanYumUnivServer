from django.contrib import admin
from rangefilter.filter import DateRangeFilter
from .models import *

# Register your models here.


class CommentAdmin(admin.ModelAdmin):
    list_display = ['commentid', 'nickname',
                    'menualias', 'date', 'new_column', 'storeid']
    list_filter = (
        'storeid',
        ('date', DateRangeFilter)
    )
    search_fields = ['nickname', 'uid', 'text', 'menualias']


admin.site.register(Comment, CommentAdmin)


class MenuAdmin(admin.ModelAdmin):
    list_display = ['menuid', 'storeid', 'menualias', 'commentid', 'score']
    list_filter = ('storeid', 'cost', 'score')
    search_fields = ['storeid', 'menualias']


admin.site.register(Menu, MenuAdmin)


class StoreAdmin(admin.ModelAdmin):
    list_display = ['storeid', 'address', 'score', 'commentid',
                    'category', 'monthlyvote', 'like', 'gendate', 'scorecount']
    list_filter = ('score', 'category',
                   ('gendate', DateRangeFilter))
    search_fields = ['storeid']


admin.site.register(Store, StoreAdmin)


class UserAdmin(admin.ModelAdmin):
    list_display = ['uid', 'nickname', 'userlevel',
                    'postid', 'path', 'registerdate']
    list_filter = ('userlevel',
                   ('registerdate', DateRangeFilter),
                   )
    search_fields = ['uid', 'nickname']


admin.site.register(User, UserAdmin)
