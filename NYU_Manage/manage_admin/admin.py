from django.contrib import admin
from rangefilter.filter import DateRangeFilter
from .models import *

# Register your models here.


class LikeAdmin(admin.ModelAdmin):
    list_display = ['likeid', 'useralias',
                    'storeid', 'show', 'updatedate']
    list_filter = (
        'likeid',
        ('updatedate', DateRangeFilter)
    )
    search_fields = ['likeid', 'useralias', 
                    'storeid', 'show', 'updatedate']


admin.site.register(Like, LikeAdmin)


class MenuAdmin(admin.ModelAdmin):
    list_display = ['menuid', 'menualias', 'storeid', 
                    'cost', 'choicecount']
    list_filter = (
        'menuid',
        'cost',
        'choicecount'
    )
    search_fields = ['menuid', 'menualias', 'storeid', 
                    'cost', 'choicecount']


admin.site.register(Menu, MenuAdmin)


class ReportAdmin(admin.ModelAdmin):
    list_display = ['reportid', 'reviewid', 'report']
    list_filter = ['reportid']
    search_fields = ['reportid', 'reviewid', 'report']


admin.site.register(Report, ReportAdmin)


class ReviewAdmin(admin.ModelAdmin):
    list_display = ['reviewid', 'useralias', 'storeid', 
                    'menuid', 'score', 'content', 
                    'imagepath', 'show', 'registerdate']
    list_filter = (
        'reviewid',
        ('registerdate', DateRangeFilter)
    )
    search_fields = ['reviewid', 'useralias', 'storeid', 
                    'menuid', 'score', 'content', 
                    'imagepath', 'show', 'registerdate']


admin.site.register(Review, ReviewAdmin)


class SaveAdmin(admin.ModelAdmin):
    list_display = ['saveid', 'useralias', 'storeid', 
                    'show', 'updatedate']
    list_filter = (
        'saveid',
        ('updatedate', DateRangeFilter)
    )
    search_fields = ['saveid', 'useralias', 'storeid', 
                    'show', 'updatedate']


admin.site.register(Save, SaveAdmin)


class StoreAdmin(admin.ModelAdmin):
    list_display = ['storeid', 'storealias', 'address', 
                    'category', 'mapx', 'mapy', 
                    'score', 'scorecount', 'likecount',
                    'savecount', 'imagepath', 'registerdate', 
                    'updatedate']
    list_filter = (
        'storeid',
        'scorecount',
        'likecount',
        'savecount', 
        ('updatedate', DateRangeFilter)
    )
    search_fields = ['storeid', 'storealias', 'address', 
                    'category', 'mapx', 'mapy', 
                    'score', 'scorecount', 'likecount',
                    'savecount', 'imagepath', 'registerdate', 
                    'updatedate']


admin.site.register(Store, StoreAdmin)


class SupportAdmin(admin.ModelAdmin):
    list_display = ['supportid', 'useralias', 'type', 
                    'category', 'reviewid', 'content', 
                    'registerdate', 'reply']
    list_filter = (
        'reply',
        ('registerdate', DateRangeFilter)
    )
    search_fields = ['supportid', 'useralias', 'type', 
                    'category', 'reviewid', 'content', 
                    'registerdate', 'reply']


admin.site.register(Support, SupportAdmin)


class UserAdmin(admin.ModelAdmin):
    list_display = ['uid', 'useralias', 'userlevel', 
                    'imagepath', 'registerdate']
    list_filter = (
        'uid',
        ('registerdate', DateRangeFilter)
    )
    search_fields = ['uid', 'useralias', 'userlevel', 
                    'imagepath', 'registerdate']


admin.site.register(User, UserAdmin)
