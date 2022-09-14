# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class Comment(models.Model):
    commentid = models.AutoField(db_column='commentId', primary_key=True)  # Field name made lowercase.
    useralias = models.ForeignKey('User', models.DO_NOTHING, db_column='userAlias')  # Field name made lowercase.
    reviewid = models.ForeignKey('Review', models.DO_NOTHING, db_column='reviewId')  # Field name made lowercase.
    content = models.CharField(max_length=300)
    registerdate = models.DateField(db_column='registerDate')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'comment'


class Like(models.Model):
    likeid = models.AutoField(db_column='likeId', primary_key=True)  # Field name made lowercase.
    useralias = models.ForeignKey('User', models.DO_NOTHING, db_column='userAlias')  # Field name made lowercase.
    storeid = models.ForeignKey('Store', models.DO_NOTHING, db_column='storeId')  # Field name made lowercase.
    show = models.IntegerField()
    updatedate = models.DateTimeField(db_column='updateDate')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'like'


class Menu(models.Model):
    menuid = models.AutoField(db_column='menuId', primary_key=True)  # Field name made lowercase.
    menualias = models.CharField(db_column='menuAlias', max_length=50)  # Field name made lowercase.
    storeid = models.ForeignKey('Store', models.DO_NOTHING, db_column='storeId')  # Field name made lowercase.
    cost = models.IntegerField()
    choicecount = models.IntegerField(db_column='choiceCount')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'menu'


class Report(models.Model):
    reportid = models.AutoField(db_column='reportId', primary_key=True)  # Field name made lowercase.
    reviewid = models.ForeignKey('Review', models.DO_NOTHING, db_column='reviewId')  # Field name made lowercase.
    report = models.TextField()

    class Meta:
        managed = False
        db_table = 'report'


class Review(models.Model):
    reviewid = models.AutoField(db_column='reviewId', primary_key=True)  # Field name made lowercase.
    useralias = models.ForeignKey('User', models.DO_NOTHING, db_column='userAlias', blank=True, null=True)  # Field name made lowercase.
    storeid = models.ForeignKey('Store', models.DO_NOTHING, db_column='storeId')  # Field name made lowercase.
    menuid = models.ForeignKey(Menu, models.DO_NOTHING, db_column='menuId')  # Field name made lowercase.
    score = models.FloatField()
    content = models.CharField(max_length=500)
    imagepath = models.TextField(db_column='imagePath', blank=True, null=True)  # Field name made lowercase.
    show = models.IntegerField()
    registerdate = models.DateField(db_column='registerDate')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'review'


class Save(models.Model):
    saveid = models.AutoField(db_column='saveId', primary_key=True)  # Field name made lowercase.
    useralias = models.ForeignKey('User', models.DO_NOTHING, db_column='userAlias')  # Field name made lowercase.
    storeid = models.ForeignKey('Store', models.DO_NOTHING, db_column='storeId')  # Field name made lowercase.
    show = models.IntegerField()
    updatedate = models.DateTimeField(db_column='updateDate')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'save'


class Store(models.Model):
    storeid = models.AutoField(db_column='storeId', primary_key=True)  # Field name made lowercase.
    storealias = models.CharField(db_column='storeAlias', max_length=50)  # Field name made lowercase.
    address = models.CharField(max_length=50)
    category = models.CharField(max_length=50)
    mapx = models.CharField(max_length=20)
    mapy = models.CharField(max_length=20)
    score = models.FloatField()
    scorecount = models.IntegerField(db_column='scoreCount')  # Field name made lowercase.
    likecount = models.IntegerField(db_column='likeCount')  # Field name made lowercase.
    savecount = models.IntegerField(db_column='saveCount')  # Field name made lowercase.
    imagepath = models.TextField(db_column='imagePath', blank=True, null=True)  # Field name made lowercase.
    registerdate = models.DateField(db_column='registerDate')  # Field name made lowercase.
    updatedate = models.DateField(db_column='updateDate')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'store'
        unique_together = (('storeid', 'storealias'),)


class Support(models.Model):
    supportid = models.AutoField(db_column='supportId', primary_key=True)  # Field name made lowercase.
    useralias = models.ForeignKey('User', models.DO_NOTHING, db_column='userAlias')  # Field name made lowercase.
    type = models.CharField(max_length=4)
    category = models.CharField(max_length=50, blank=True, null=True)
    reviewid = models.ForeignKey(Review, models.DO_NOTHING, db_column='reviewId', blank=True, null=True)  # Field name made lowercase.
    content = models.TextField()
    registerdate = models.DateField(db_column='registerDate')  # Field name made lowercase.
    reply = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'support'


class User(models.Model):
    uid = models.CharField(db_column='uId', max_length=50)  # Field name made lowercase.
    useralias = models.CharField(db_column='userAlias', primary_key=True, max_length=50)  # Field name made lowercase.
    userlevel = models.CharField(db_column='userLevel', max_length=20)  # Field name made lowercase.
    imagepath = models.TextField(db_column='imagePath', blank=True, null=True)  # Field name made lowercase.
    registerdate = models.DateField(db_column='registerDate')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'user'
