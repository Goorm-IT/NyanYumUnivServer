# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class Comment(models.Model):
    # Field name made lowercase.
    commentid = models.CharField(
        db_column='commentId', primary_key=True, max_length=50)
    uid = models.CharField(max_length=50, blank=True, null=True)
    # Field name made lowercase.
    nickname = models.CharField(
        db_column='nickName', max_length=50, blank=True, null=True)
    text = models.CharField(max_length=100, blank=True, null=True)
    # Field name made lowercase.
    menualias = models.CharField(
        db_column='menuAlias', max_length=50, blank=True, null=True)
    cost = models.IntegerField(blank=True, null=True)
    # Field name made lowercase.
    date = models.DateField(db_column='Date', blank=True, null=True)
    new_column = models.IntegerField(blank=True, null=True)
    # Field name made lowercase.
    storeid = models.ForeignKey(
        'Store', models.DO_NOTHING, db_column='storeId', blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'comment'


class Menu(models.Model):
    # Field name made lowercase.
    menuid = models.CharField(
        db_column='menuId', primary_key=True, max_length=50)
    storeid = models.ForeignKey(
        'Store', models.DO_NOTHING, db_column='storeId', blank=True)
    # Field name made lowercase.
    menualias = models.CharField(
        db_column='menuAlias', max_length=50, blank=True, null=True)
    cost = models.IntegerField(blank=True, null=True)
    # Field name made lowercase.
    commentid = models.CharField(
        db_column='commentId', max_length=50, blank=True, null=True)
    score = models.FloatField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'menu'


class Store(models.Model):
    # Field name made lowercase.
    storeid = models.CharField(
        db_column='storeId', primary_key=True, max_length=50)
    address = models.CharField(max_length=50, blank=True, null=True)
    score = models.FloatField(blank=True, null=True)
    # Field name made lowercase.
    commentid = models.CharField(
        db_column='commentId', max_length=50, blank=True, null=True)
    category = models.CharField(max_length=50, blank=True)
    monthlyvote = models.IntegerField(
        blank=True, null=True, default=0)
    like = models.IntegerField(blank=True, default=0)
    gendate = models.DateField(blank=True, null=True)
    scorecount = models.IntegerField(blank=True)

    class Meta:
        managed = False
        db_table = 'store'


class User(models.Model):
    uid = models.CharField(primary_key=True, max_length=50)
    nickname = models.CharField(
        unique=True, max_length=50, blank=True, null=True)
    userlevel = models.CharField(max_length=20, blank=True, null=True)
    # Field name made lowercase.
    postid = models.CharField(
        db_column='postId', max_length=20, blank=True, null=True)
    path = models.CharField(max_length=100, blank=True, null=True)
    # Field name made lowercase.
    registerdate = models.DateField(db_column='registerDate')

    class Meta:
        managed = False
        db_table = 'user'
