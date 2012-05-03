package com.bowerstudios.passhasher



import org.junit.*
import grails.test.mixin.*

@TestFor(PlaceController)
@Mock(Place)
class PlaceControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/place/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.placeInstanceList.size() == 0
        assert model.placeInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.placeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.placeInstance != null
        assert view == '/place/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/place/show/1'
        assert controller.flash.message != null
        assert Place.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/place/list'


        populateValidParams(params)
        def place = new Place(params)

        assert place.save() != null

        params.id = place.id

        def model = controller.show()

        assert model.placeInstance == place
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/place/list'


        populateValidParams(params)
        def place = new Place(params)

        assert place.save() != null

        params.id = place.id

        def model = controller.edit()

        assert model.placeInstance == place
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/place/list'

        response.reset()


        populateValidParams(params)
        def place = new Place(params)

        assert place.save() != null

        // test invalid parameters in update
        params.id = place.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/place/edit"
        assert model.placeInstance != null

        place.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/place/show/$place.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        place.clearErrors()

        populateValidParams(params)
        params.id = place.id
        params.version = -1
        controller.update()

        assert view == "/place/edit"
        assert model.placeInstance != null
        assert model.placeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/place/list'

        response.reset()

        populateValidParams(params)
        def place = new Place(params)

        assert place.save() != null
        assert Place.count() == 1

        params.id = place.id

        controller.delete()

        assert Place.count() == 0
        assert Place.get(place.id) == null
        assert response.redirectedUrl == '/place/list'
    }
}
