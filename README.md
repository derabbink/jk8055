jk8055
======

Java control application for Velleman's K8055 USB experiment interface board.

Origin
--------

This is a fork of Mario Boikov's jk8055 project.

Mario is the original author. See his project [here][1]  
[This][2] is a fork of his project.

Dependencies
--------

You need to clone [libusb4j][3] first and run `mvn install` on it.

Usage
--------

Either run the class `org.beblue.k8055.Main` as a Java Application from Eclipse,
or run `mvn package` followed by `java -jar target/jk8055-0.0.1-SNAPSHOT.jar` in a terminal.

If either approach fails with a JNA UnsatisfiedLinkError claiming it cannot load a library, try
adding the `-Djna.library.path=` option. On OS X this could look like this:
`java -Djna.library.path=/opt/local/lib -jar target/jk8055-0.0.1-SNAPSHOT.jar`, depending on where
you installed libusb. 

If you'd like to modify the source code, here are some pointers
*  Clone the repo and import it as an "Existing Maven Project" into Eclipse (requires m2e).
*  Don't forget about the [GPL v2 license][4]
*  Feel free to contact me if you found/fixed bugs or made any other changes worth sharing here.

License
--------

The project is licensed under the [GPL v2 license][3].  
This means you can modify the code, but you also have to publish the modifications under the same license.  
You may not distribute the application without distributing its source code.

  [1]: https://launchpad.net/jk8055 "jk8055 on launchpad.net"
  [2]: https://github.com/derabbink/jk8055 on github"
  [3]: https://github.com/derabbink/libusb4j "libusb4j github"
  [4]: http://www.gnu.org/licenses/gpl-2.0.html "GPL v2 license"
