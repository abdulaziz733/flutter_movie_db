package com.aziz.flutter_movie_db.utils

import android.content.Context
import io.realm.*
import io.realm.exceptions.RealmException

/**
 * Created by hendry033749 on 01/09/2016.
 */

class RealmHelper {

    fun setRealmInit(context: Context) {

        Realm.init(context)
        Realm.setDefaultConfiguration(
                RealmConfiguration.Builder()
                        .name("Moviedb-realm")
                        .deleteRealmIfMigrationNeeded()
                        .build())
    }

    fun <E : RealmObject> getAll(realm: Realm, objectClass: Class<E>): List<E> {
        if (!realm.isClosed) {
            return realm.where(objectClass).findAll()
        }
        return RealmList()
    }


    fun <E : RealmObject> getAllSorted(realm: Realm, objectClass: Class<E>,
                                       fieldName: String, isAscending: Boolean): List<E> {
        var sort = Sort.ASCENDING
        if (!isAscending) sort = Sort.DESCENDING
        return realm.where(objectClass).findAll().sort(fieldName, sort)
    }

    fun <E : RealmObject> getAllWhere(realm: Realm, objectClass: Class<E>, columnName: String, key: String): List<E> {
        return realm.where(objectClass).equalTo(columnName, key).findAll()
    }


    fun <E : RealmObject> getAllTwoWhere(realm: Realm, objectClass: Class<E>, columnName: String, key: String, columnName2: String, key2: String): List<E> {
        return realm.where(objectClass).equalTo(columnName, key).equalTo(columnName2, key2).findAll()
    }

    fun <E : RealmObject> getAllWhere(realm: Realm, objectClass: Class<E>, columnName: String, key: Int): List<E> {
        return realm.where(objectClass).equalTo(columnName, key).findAll()
    }

    fun <E : RealmObject> getAllWhere(realm: Realm, objectClass: Class<E>, columnName: String, key: Int, columnName2: String, key2: String): List<E> {
        return realm.where(objectClass).equalTo(columnName, key).equalTo(columnName2, key2).findAll()
    }

    fun <E : RealmObject> getAllWhere(realm: Realm, objectClass: Class<E>, columnName: String, key: Boolean): List<E> {
        return realm.where(objectClass).equalTo(columnName, key).findAll()
    }


    fun <E : RealmObject> getAllWhereSorted(realm: Realm, objectClass: Class<E>, columnName: String, key: Boolean,
                                            fieldName: String, isAscending: Boolean): List<E> {
        var sort = Sort.ASCENDING
        if (!isAscending) sort = Sort.DESCENDING
        return realm.where(objectClass).equalTo(columnName, key).findAll().sort(fieldName, sort)
    }

    fun <E : RealmObject> getAllWhereSorted(realm: Realm, objectClass: Class<E>, columnName: String, key: Boolean,
                                            fieldName: String, isAscending: Boolean,
                                            fieldName2: String, keyWord2: String): List<E> {
        var sort = Sort.ASCENDING
        if (!isAscending) sort = Sort.DESCENDING
        return realm.where(objectClass).equalTo(columnName, key).equalTo(fieldName2, keyWord2).findAll().sort(fieldName, sort)
    }

    fun <E : RealmObject> getAllWhereSorted(realm: Realm, objectClass: Class<E>, columnName: String, key: String,
                                            fieldName: String, isAscending: Boolean): List<E> {
        var sort = Sort.ASCENDING
        if (!isAscending) sort = Sort.DESCENDING
        return realm.where(objectClass).equalTo(columnName, key).findAll().sort(fieldName, sort)
    }

    fun <E : RealmObject> getAllLikeSorted(realm: Realm, objectClass: Class<E>, columnName: String, key: String): List<E> {
        return realm.where(objectClass).contains(columnName, key, Case.INSENSITIVE).findAllAsync()
    }

    fun <E : RealmObject> getAllLikeSorted(realm: Realm, objectClass: Class<E>, columnName: String, key: String,
                                           fieldName: String, isAscending: Boolean): List<E> {
        var sort = Sort.ASCENDING
        if (!isAscending) sort = Sort.DESCENDING
        return realm.where(objectClass).contains(columnName, key, Case.INSENSITIVE).findAllAsync().sort(fieldName, sort)
    }

    fun <E : RealmObject> getAllWhereLikeSorted(realm: Realm, objectClass: Class<E>, fieldName: String, keyField: Boolean, columnName: String, key: String): List<E> {
        return realm.where(objectClass).equalTo(fieldName, keyField).contains(columnName, key, Case.INSENSITIVE).findAllAsync()
    }

    fun <E : RealmObject> getAllWhereLikeSort(realm: Realm, objectClass: Class<E>, fieldName: String, keyField: String, columnSort: String, isAscending: Boolean): List<E> {
        var sort = Sort.ASCENDING
        if (!isAscending) sort = Sort.DESCENDING
        return realm.where(objectClass).contains(fieldName, keyField, Case.INSENSITIVE).sort(columnSort, sort).findAllAsync()
    }

    fun <E : RealmObject> getAllWhereLikeSorted(realm: Realm, objectClass: Class<E>, fieldName: String, keyField: Boolean,
                                                fieldName2: String, keyField2: String,
                                                columnName: String, key: String): List<E> {
        return realm.where(objectClass).equalTo(fieldName, keyField).equalTo(fieldName2, keyField2).contains(columnName, key, Case.INSENSITIVE).findAllAsync()
    }

    fun <E : RealmObject> countAll(realm: Realm, objectClass: Class<E>): Long {
        return realm.where(objectClass).count()
    }

    fun <E : RealmObject> countAllWhere(realm: Realm, objectClass: Class<E>,
                                        columnSearch: String, keyWord: String): Long {
        return realm.where(objectClass).equalTo(columnSearch, keyWord, Case.INSENSITIVE).count()
    }

    fun <E : RealmObject> countAllWhereAnd(realm: Realm, objectClass: Class<E>,
                                           columnSearch: String, keyWord: Boolean): Long {
        return realm.where(objectClass).equalTo(columnSearch, keyWord).count()
    }

    fun <E : RealmObject> countAllWhereAnd(realm: Realm, objectClass: Class<E>,
                                           columnSearch1: String, keyWord1: Boolean,
                                           columnSearch2: String, keyWord2: Long): Long {
        return realm.where(objectClass).equalTo(columnSearch1, keyWord1)
                .equalTo(columnSearch2, keyWord2).count()
    }

    fun <E : RealmObject> find(realm: Realm, objectClass: Class<E>,
                               column: String, value: String): E? {
        return realm.where(objectClass).equalTo(column, value).findFirst()
    }

    fun <E : RealmObject> findFirst(realm: Realm, objectClass: Class<E>): E? {
        return realm.where(objectClass).findFirst()
    }

    fun <E : RealmObject> find(realm: Realm, objectClass: Class<E>,
                               column1: String, value1: String,
                               column2: String, value2: String): E? {
        return realm.where(objectClass).equalTo(column1, value1).equalTo(column2, value2).findFirst()
    }

    fun <E : RealmObject> find(realm: Realm, objectClass: Class<E>,
                               column1: String, value1: Int,
                               column2: String, value2: Int): E? {
        return realm.where(objectClass).equalTo(column1, value1).equalTo(column2, value2).findFirst()
    }

    fun <E : RealmObject> find(realm: Realm, objectClass: Class<E>,
                               column1: String, value1: String,
                               column2: String, value2: String,
                               column3: String, value3: String,
                               column4: String, value4: String,
                               column5: String, value5: String): E? {
        return realm.where(objectClass).equalTo(column1, value1).equalTo(column2, value2).equalTo(column3, value3).equalTo(column4, value4).equalTo(column5, value5).findFirst()
    }

    fun <E : RealmObject> find(realm: Realm, objectClass: Class<E>,
                               column: String, value: Int): E? {
        return realm.where(objectClass).equalTo(column, value).findFirst()
    }

    fun <E : RealmObject> find(realm: Realm, objectClass: Class<E>,
                               column: String, value: Long): E? {
        return realm.where(objectClass).equalTo(column, value).findFirst()
    }

    fun <E : RealmObject> findAnd(realm: Realm, objectClass: Class<E>,
                                  column1: String, value1: Long,
                                  column2: String, value2: Long): E? {
        return realm.where(objectClass).equalTo(column1, value1)
                .equalTo(column2, value2).findFirst()
    }

    fun <E : RealmObject> findAnd(realm: Realm, objectClass: Class<E>,
                                  column1: String, value1: Int,
                                  column2: String, value2: String): E? {
        return realm.where(objectClass).equalTo(column1, value1)
                .equalTo(column2, value2).findFirst()
    }

    fun <E : RealmObject> findAll(realm: Realm, objectClass: Class<E>,
                                  columnSearch: String, keyWord: Boolean): List<E> {
        return realm.where(objectClass).equalTo(columnSearch, keyWord).findAll()
    }

    fun <E : RealmObject> findAll(realm: Realm, objectClass: Class<E>,
                                  columnSearch: String, keyWord: String): List<E> {
        return realm.where(objectClass).equalTo(columnSearch, keyWord, Case.INSENSITIVE).findAll()
    }

    fun <E : RealmObject> findAll(realm: Realm, objectClass: Class<E>,
                                  columnSearch: String, keyWord: String,
                                  column2: String, keyWord2: String): List<E> {
        return realm.where(objectClass).equalTo(columnSearch, keyWord, Case.INSENSITIVE).equalTo(column2, keyWord2, Case.INSENSITIVE).findAll()
    }

    fun <E : RealmObject> findAll(realm: Realm, objectClass: Class<E>,
                                  columnSearch: String, keyWord: Boolean,
                                  column2: String, keyWord2: String): List<E> {
        return realm.where(objectClass).equalTo(columnSearch, keyWord).equalTo(column2, keyWord2, Case.INSENSITIVE).findAll()
    }

    fun <E : RealmObject> findAll(realm: Realm, objectClass: Class<E>,
                                  column1: String, value1: String,
                                  column2: String, value2: String,
                                  column3: String, value3: String,
                                  column4: String, value4: String,
                                  column5: String, value5: String): List<E> {
        return realm.where(objectClass).equalTo(column1, value1).equalTo(column2, value2).equalTo(column3, value3).equalTo(column4, value4).equalTo(column5, value5).findAll()
    }

    fun <E : RealmObject> findAllSorted(realm: Realm, objectClass: Class<E>,
                                        columnSearch: String, keyWord: String,
                                        columnSort: String, isAscending: Boolean): List<E> {
        var sort = Sort.ASCENDING
        if (!isAscending) sort = Sort.DESCENDING
        return realm.where(objectClass).equalTo(columnSearch, keyWord, Case.INSENSITIVE).findAll().sort(columnSort, sort)
    }

    fun <E : RealmObject> findAllSorted(realm: Realm, objectClass: Class<E>,
                                        columnSearch: String, keyWord: Boolean,
                                        columnSort: String, isAscending: Boolean): List<E> {
        var sort = Sort.ASCENDING
        if (!isAscending) sort = Sort.DESCENDING
        return realm.where(objectClass).equalTo(columnSearch, keyWord).findAll().sort(columnSort, sort)
    }

    fun <E : RealmObject> findAllSorted(realm: Realm, objectClass: Class<E>,
                                        columnSearch: String, keyWord: Long,
                                        columnSort: String, isAscending: Boolean): List<E> {
        var sort = Sort.ASCENDING
        if (!isAscending) sort = Sort.DESCENDING
        return realm.where(objectClass).equalTo(columnSearch, keyWord).findAll().sort(columnSort, sort)
    }

    fun <E : RealmObject> findAllSorted(realm: Realm, objectClass: Class<E>,
                                        columnSearch: String, keyWord: Int,
                                        columnSort: String, isAscending: Boolean): List<E> {
        var sort = Sort.ASCENDING
        if (!isAscending) sort = Sort.DESCENDING
        return realm.where(objectClass).equalTo(columnSearch, keyWord).findAll().sort(columnSort, sort)
    }

    fun <E : RealmObject> findAllGrouped(realm: Realm, objectClass: Class<E>,
                                         columnGroupBy: String): List<E> {
        return realm.where(objectClass).distinct(columnGroupBy).findAll()
    }

    fun <E : RealmObject> add(RealmObject: E) {
        val realm = Realm.getDefaultInstance()
        try {
            realm.beginTransaction()
            realm.copyToRealm(RealmObject)
            realm.commitTransaction()
        } catch (e: RealmException) {
            realm.cancelTransaction()
        } finally {
            realm.close()
        }
    }

    fun <E : RealmObject> addAll(RealmObjectList: List<E>) {
        val realm = Realm.getDefaultInstance()
        try {
            realm.beginTransaction()
            realm.copyToRealm(RealmObjectList)
            realm.commitTransaction()
        } catch (e: RealmException) {
            realm.cancelTransaction()
        } finally {
            realm.close()
        }
    }

    fun <E : RealmObject> update(RealmObject: E) {
        val realm = Realm.getDefaultInstance()
        try {
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(RealmObject)
            realm.commitTransaction()
        } catch (e: RealmException) {
            realm.cancelTransaction()
        } finally {
            realm.close()
        }
    }

    fun <E : RealmObject> updateAll(realmObjectList: List<E>) {
        val realm = Realm.getDefaultInstance()
        try {
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(realmObjectList)
            realm.commitTransaction()
        } catch (e: RealmException) {
            realm.cancelTransaction()
        } finally {
            realm.close()
        }
    }

    fun <E : RealmObject> deleteWhere(objectClass: Class<E>,
                                      columnSearch: String, keyWord: String) {
        val realm = Realm.getDefaultInstance()
        val record = realm.where(objectClass).equalTo(columnSearch, keyWord).findFirst()
        try {
            realm.beginTransaction()
            record!!.deleteFromRealm()
            realm.commitTransaction()
        } catch (e: RealmException) {
            realm.cancelTransaction()
        } finally {
            realm.close()
        }
    }

    fun <E : RealmObject> deleteWhere(objectClass: Class<E>,
                                      columnSearch: String, keyWord: Int) {
        val realm = Realm.getDefaultInstance()
        val record = realm.where(objectClass).equalTo(columnSearch, keyWord).findFirst()
        try {
            realm.beginTransaction()
            record!!.deleteFromRealm()
            realm.commitTransaction()
        } catch (e: RealmException) {
            realm.cancelTransaction()
        } finally {
            realm.close()
        }
    }

    fun <E : RealmObject> deleteWhere(objectClass: Class<E>,
                                      column1: String, value1: String,
                                      column2: String, value2: String) {
        val realm = Realm.getDefaultInstance()
        val record = realm.where(objectClass).equalTo(column1, value1).equalTo(column2, value2).findFirst()
        try {
            realm.beginTransaction()
            record!!.deleteFromRealm()
            realm.commitTransaction()
        } catch (e: RealmException) {
            realm.cancelTransaction()
        } finally {
            realm.close()
        }
    }


    fun <E : RealmObject> deleteWhere(objectClass: Class<E>,
                                      column1: String, value1: Boolean) {
        val realm = Realm.getDefaultInstance()
        val record = realm.where(objectClass).equalTo(column1, value1).findFirst()
        try {
            realm.beginTransaction()
            record!!.deleteFromRealm()
            realm.commitTransaction()
        } catch (e: RealmException) {
            realm.cancelTransaction()
        } finally {
            realm.close()
        }
    }

    fun <E : RealmObject> deleteWhere(objectClass: Class<E>,
                                      column1: String, value1: String,
                                      column2: String, value2: String,
                                      column3: String, value3: String,
                                      column4: String, value4: String,
                                      column5: String, value5: String) {
        val realm = Realm.getDefaultInstance()
        val record = realm.where(objectClass).equalTo(column1, value1).equalTo(column2, value2).equalTo(column3, value3).equalTo(column4, value4).equalTo(column5, value5).findFirst()
        try {
            realm.beginTransaction()
            record!!.deleteFromRealm()
            realm.commitTransaction()
        } catch (e: RealmException) {
            realm.cancelTransaction()
        } finally {
            realm.close()
        }
    }

    fun <E : RealmObject> deleteWhere(objectClass: Class<E>,
                                      columnSearch: String, keyWord: Long) {
        val realm = Realm.getDefaultInstance()
        try {
            realm.beginTransaction()
            realm.where(objectClass).equalTo(columnSearch, keyWord).findFirst()!!.deleteFromRealm()
            realm.commitTransaction()
        } catch (e: RealmException) {
            realm.cancelTransaction()
        } finally {
            realm.close()
        }
    }

    fun <E : RealmObject> deleteAllWhere(objectClass: Class<E>,
                                         columnSearch: String, keyWord: Long) {
        val realm = Realm.getDefaultInstance()
        try {
            realm.beginTransaction()
            realm.where(objectClass).equalTo(columnSearch, keyWord).findAll().deleteAllFromRealm()
            realm.commitTransaction()
        } catch (e: RealmException) {
            realm.cancelTransaction()
        } finally {
            realm.close()
        }
    }

    fun <E : RealmObject> deleteAllWhere(objectClass: Class<E>,
                                         columnSearch: String, keyWord: String) {
        val realm = Realm.getDefaultInstance()
        try {
            realm.beginTransaction()
            realm.where(objectClass).equalTo(columnSearch, keyWord).findAll().deleteAllFromRealm()
            realm.commitTransaction()
        } catch (e: RealmException) {
            realm.cancelTransaction()
        } finally {
            realm.close()
        }
    }


    fun <E : RealmObject> deleteAllWhere(objectClass: Class<E>,
                                         columnSearch: String, keyWord: Boolean) {
        val realm = Realm.getDefaultInstance()
        try {
            realm.beginTransaction()
            realm.where(objectClass).equalTo(columnSearch, keyWord).findAll().deleteAllFromRealm()
            realm.commitTransaction()
        } catch (e: RealmException) {
            realm.cancelTransaction()
        } finally {
            realm.close()
        }
    }


    fun <E : RealmObject> deleteAllWhere(objectClass: Class<E>,
                                         columnSearch: String, keyWord: Boolean, columnSearch2: String, keyWord2: String) {
        val realm = Realm.getDefaultInstance()
        try {
            realm.beginTransaction()
            realm.where(objectClass).equalTo(columnSearch, keyWord).equalTo(columnSearch2, keyWord2).findAll().deleteAllFromRealm()
            realm.commitTransaction()
        } catch (e: RealmException) {
            realm.cancelTransaction()
        } finally {
            realm.close()
        }
    }

    fun <E : RealmObject> deleteAllWhere(objectClass: Class<E>,
                                         columnSearch: String, keyWord: Float, columnSearch2: String, keyWord2: Float) {
        val realm = Realm.getDefaultInstance()
        try {
            realm.beginTransaction()
            realm.where(objectClass).equalTo(columnSearch, keyWord).equalTo(columnSearch2, keyWord2).findAll().deleteAllFromRealm()
            realm.commitTransaction()
        } catch (e: RealmException) {
            realm.cancelTransaction()
        } finally {
            realm.close()
        }
    }

    fun <E : RealmObject> deleteAllWhere(objectClass: Class<E>,
                                         columnSearch: String, keyWord: Int) {
        val realm = Realm.getDefaultInstance()
        try {
            realm.beginTransaction()
            realm.where(objectClass).equalTo(columnSearch, keyWord).findAll().deleteAllFromRealm()
            realm.commitTransaction()
        } catch (e: RealmException) {
            realm.cancelTransaction()
        } finally {
            realm.close()
        }
    }

    fun deleteAll(objectClass: Class<out RealmObject>) {
        val realm = Realm.getDefaultInstance()
        try {
            realm.beginTransaction()
            realm.delete(objectClass)
            realm.commitTransaction()
        } catch (e: RealmException) {
            realm.cancelTransaction()
        } finally {
            realm.close()
        }
    }

}
