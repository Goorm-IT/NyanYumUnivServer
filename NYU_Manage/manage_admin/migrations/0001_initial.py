# Generated by Django 4.0.1 on 2022-03-22 08:16

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Comment',
            fields=[
                ('commentid', models.CharField(db_column='commentId', max_length=50, primary_key=True, serialize=False)),
                ('uid', models.CharField(blank=True, max_length=50, null=True)),
                ('nickname', models.CharField(blank=True, db_column='nickName', max_length=50, null=True)),
                ('text', models.CharField(blank=True, max_length=100, null=True)),
                ('menualias', models.CharField(blank=True, db_column='menuAlias', max_length=50, null=True)),
                ('cost', models.IntegerField(blank=True, null=True)),
                ('date', models.DateField(blank=True, db_column='Date', null=True)),
                ('new_column', models.IntegerField(blank=True, null=True)),
            ],
            options={
                'db_table': 'comment',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='Menu',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('storeid', models.CharField(db_column='storeId', max_length=50)),
                ('menualias', models.CharField(blank=True, db_column='menuAlias', max_length=50, null=True)),
                ('cost', models.IntegerField(blank=True, null=True)),
                ('commentid', models.CharField(blank=True, db_column='commentId', max_length=50, null=True)),
                ('score', models.FloatField(blank=True, null=True)),
            ],
            options={
                'db_table': 'menu',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='Store',
            fields=[
                ('storeid', models.CharField(db_column='storeId', max_length=50, primary_key=True, serialize=False)),
                ('address', models.CharField(blank=True, max_length=50, null=True)),
                ('score', models.FloatField(blank=True, null=True)),
                ('commentid', models.CharField(blank=True, db_column='commentId', max_length=50, null=True)),
            ],
            options={
                'db_table': 'store',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='User',
            fields=[
                ('uid', models.CharField(max_length=50, primary_key=True, serialize=False)),
                ('nickname', models.CharField(blank=True, max_length=50, null=True, unique=True)),
                ('userlevel', models.CharField(blank=True, max_length=20, null=True)),
                ('postid', models.CharField(blank=True, db_column='postId', max_length=20, null=True)),
                ('path', models.CharField(blank=True, max_length=100, null=True)),
                ('registerdate', models.DateField(db_column='registerDate')),
            ],
            options={
                'db_table': 'user',
                'managed': False,
            },
        ),
    ]