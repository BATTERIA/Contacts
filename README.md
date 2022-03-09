# Contacts App<br/>

App for contacts.

## Details

### Avatars and Detail Page
I used horizontal ViewPager2 to show contact details.
And for achieving the goal effect of avatars' layout, TabLayout is not enough.
Hence I used RecyclerView and LinearSnapHelper to let avatar keep center.
And in order to let the first and last one to keep center, I used two placeholder to achieve the effect.

### Mediator
Also I need to complete the Mediator between the ViewPager2 and the Avatar RecyclerView so that they can scroll together 
just like TabLayoutMediator to combine with ViewPager2.

### About Information text
In case there is too much information, I need to use ScrollView.
And I have to solve the sliding conflict between this and ViewPager2 outside,
so I disallow parent ViewGroup to intercept TouchEvent when inner ScrollView is not at the top or bottom.

### ImageLoader and ContactCache
I designed three-level cache of both ImageLoader and ContactCache for further extensibility.

## Screen Record<br/>

### Android 10.0<br/>
![Android10](https://github.com/BATTERIA/Contacts/blob/44b04c17400bbc524bdf145e2a928032216d280d/screenrecord/Android10.gif)

### Android 8.0<br/>
![android8](https://github.com/BATTERIA/Contacts/blob/44b04c17400bbc524bdf145e2a928032216d280d/screenrecord/android8.gif)

### Android 5.0<br/>
![android5](https://github.com/BATTERIA/Contacts/blob/44b04c17400bbc524bdf145e2a928032216d280d/screenrecord/android5.gif)

### Dark Mode<br/>
![dark-mode](https://github.com/BATTERIA/Contacts/blob/44b04c17400bbc524bdf145e2a928032216d280d/screenrecord/Android10dark.gif)

### Rotate the Screen<br/>
![Android10-Horizontal](https://github.com/BATTERIA/Contacts/blob/44b04c17400bbc524bdf145e2a928032216d280d/screenrecord/Android10-Horizontal.gif)









