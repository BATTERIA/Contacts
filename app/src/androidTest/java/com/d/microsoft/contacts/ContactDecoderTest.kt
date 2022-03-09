package com.d.microsoft.contacts

import com.d.microsoft.contacts.model.decode.ContactDecoder
import com.d.microsoft.contacts.model.decode.IContactDecoder
import junit.framework.TestCase
import org.json.JSONArray
import org.json.JSONObject

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/9
 * @description: UT for ContactDecoder
 */
class ContactDecoderTest : TestCase() {
    private var contactDecoder: IContactDecoder? = null

    public override fun setUp() {
        super.setUp()
        contactDecoder = ContactDecoder()
    }

    fun testJsonToContactFromString() {
        contactDecoder?.jsonToContact(JSON)?.apply {
            assertEquals(firstName, "Allan")
            assertEquals(lastName, "Munger")
            assertEquals(avatarFileName, "Allan Munger.png")
            assertEquals(title, "Writer")
        }
    }

    fun testJsonToContactFromError() {
        val entity = contactDecoder?.jsonToContact(ERROR_JSON)
        assertEquals(entity, null)
    }

    fun testJsonToContactFromJSONArray() {
        contactDecoder?.jsonToContact(JSONObject(JSON))?.apply {
            assertEquals(firstName, "Allan")
            assertEquals(lastName, "Munger")
            assertEquals(avatarFileName, "Allan Munger.png")
            assertEquals(title, "Writer")
        }
    }

    fun testJsonToContactsFromString() {
        contactDecoder?.jsonToContacts(JSON_ARRAY)?.get(0)?.apply {
            assertEquals(firstName, "Allan")
            assertEquals(lastName, "Munger")
            assertEquals(avatarFileName, "Allan Munger.png")
            assertEquals(title, "Writer")
        }
    }

    fun testJsonToContactsFromStringError() {
        val entity = contactDecoder?.jsonToContacts(ERROR_JSON)
        assertEquals(entity, null)
    }

    fun testJsonToContactsFromJSONArray() {
        contactDecoder?.jsonToContacts(JSONArray(JSON_ARRAY))?.get(0)?.apply {
            assertEquals(firstName, "Allan")
            assertEquals(lastName, "Munger")
            assertEquals(avatarFileName, "Allan Munger.png")
            assertEquals(title, "Writer")
        }
    }



    companion object {
        private const val JSON = "{\n" +
                "  \"first_name\": \"Allan\",\n" +
                "  \"last_name\": \"Munger\",\n" +
                "  \"avatar_filename\": \"Allan Munger.png\",\n" +
                "  \"title\": \"Writer\",\n" +
                "  \"introduction\": \"Ut malesuada sollicitudin tincidunt. Maecenas volutpat suscipit efficitur. Curabitur ut tortor sit amet lacus pellentesque convallis in laoreet lectus. Curabitur lorem velit, bibendum et vulputate vulputate, commodo in tortor. Curabitur a dapibus mauris. Vestibulum hendrerit euismod felis at hendrerit. Pellentesque imperdiet volutpat molestie. Nam vehicula dui eu consequat finibus. Phasellus sed placerat lorem. Nulla pretium a magna sit amet iaculis. Aenean eget eleifend elit. Ut eleifend aliquet interdum. Cras pulvinar elit a dapibus iaculis. Nullam fermentum porttitor ultrices.\"\n" +
                "}"
        private const val JSON_ARRAY = "[\n" +
                "  {\n" +
                "    \"first_name\": \"Allan\",\n" +
                "    \"last_name\": \"Munger\",\n" +
                "    \"avatar_filename\": \"Allan Munger.png\",\n" +
                "    \"title\": \"Writer\",\n" +
                "    \"introduction\": \"Ut malesuada sollicitudin tincidunt. Maecenas volutpat suscipit efficitur. Curabitur ut tortor sit amet lacus pellentesque convallis in laoreet lectus. Curabitur lorem velit, bibendum et vulputate vulputate, commodo in tortor. Curabitur a dapibus mauris. Vestibulum hendrerit euismod felis at hendrerit. Pellentesque imperdiet volutpat molestie. Nam vehicula dui eu consequat finibus. Phasellus sed placerat lorem. Nulla pretium a magna sit amet iaculis. Aenean eget eleifend elit. Ut eleifend aliquet interdum. Cras pulvinar elit a dapibus iaculis. Nullam fermentum porttitor ultrices.\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"first_name\": \"Amanda\",\n" +
                "    \"last_name\": \"Brady\",\n" +
                "    \"avatar_filename\": \"Amanda Brady.png\",\n" +
                "    \"title\": \"Sales Representative\",\n" +
                "    \"introduction\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam pulvinar neque in ullamcorper finibus. Aliquam ante orci, elementum non efficitur id, commodo ac velit. Proin non ornare neque, ac ornare odio. Nullam imperdiet tellus lacinia, semper justo vel, elementum metus. Aenean eget diam at quam dignissim varius. Nunc sed urna vehicula ipsum efficitur volutpat. Mauris vel augue ut magna tincidunt imperdiet. Integer sit amet vestibulum justo. Aenean placerat, nibh ac accumsan tincidunt, lorem arcu maximus justo, sed elementum tellus nisi id purus. Sed ac porttitor orci. Etiam et augue ullamcorper nibh mattis pharetra. Suspendisse ac mauris nec velit euismod rhoncus. Vestibulum tempor magna purus, id lacinia erat tempus eget.\"\n" +
                "  }\n" +
                "]"

        private const val ERROR_JSON = "}{"
    }
}