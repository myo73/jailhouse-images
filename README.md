Jailhouse Image
===============

The goal of this project is to generate ready-to-use reference images for the
[Jailhouse hypervisor](https://github.com/siemens/jailhouse) to support
demonstration and testing. The images are generated from Debian packages using
the [Isar build system](https://github.com/ilbers/isar).


Quickstart for Virtual Targets
------------------------------

The host-side requirements are:

- Docker (tested with 17.09.1-ce)
- QEMU >= 2.8 for x86 image, >= 2.12 for ARM64 image
- Kernel >= 4.4 with KVM support (for x86 image)
- On Intel, kvm_intel module loaded with parameter `nested=1`

To build a target image, just run `build-images.sh` and select one (or
both) of the QEMU targets. The generated image can then be executed using
`start-qemu.sh ARCHITECTURE`. Currently supported are `x86` and `arm64` as
architectures.


Quickstart for Physical Targets
-------------------------------

Call `build-images.sh` and select the desired target. Afterwards, flash the
image on an empty SD card, e.g.:

    dd if=out/build/tmp/deploy/images/demo-image-debian-stretch-orangepi-zero.wic.img \
       of=/dev/mmcblk0 bs=4M status=progress

### Orange Pi Zero

The [Orange Pi Zero](http://www.orangepi.org/orangepizero) is supported with
its 256 MB edition. Ethernet is supported out of the box with the generated
image. To configure the WLAN interface on this board, create
`/etc/network/interfaces.d/wlan0` with the following content:

    allow-hotplug wlan0

    iface wlan0 inet dhcp
        wpa-ssid <your wlan ssid>
        wpa-psk <your wlan key>

Note that the driver and the WLAN firmware are of experimental quality and have
significant reception latency problems. In contrast, the LAN interface works
smoothly.

### NUC6CAY

The [NUC6CAY](https://www.intel.com/content/www/us/en/products/boards-kits/nuc/mini-pcs/nuc6cays.html)
is supported with 8 GB of RAM. It can boot from an SD card, or you can flash
the generated on a built-in storage device. The device has to boot in EFI mode.

As the device comes without a UART connector, the output of Jailhouse can only
be seen via the EFI framebuffer on a monitor or on the virtual Jailhouse
console (`jailhouse console`).

### ESPRESSObin

The [ESPRESSObin](http://espressobin.net/tech-spec/) 1 GB edition is supported.
Before being able to boot the SD card image, the pre-installed U-Boot needs
further manual tuning (because the old vendor U-Boot lacks distro support).
Attach to the serial port of the board and type the following on the U-Boot
command line:

    setenv bootcmd "load mmc 0:1 0x4d00000 /boot/boot.scr; source 0x4d00000"
    saveenv
    reset

After that, the board will automatically start from the generated SD card
image.

### MACCHIATObin

The [MACCHIATObin](http://macchiatobin.net/compare/) is supported in both
variants. Same story as with the ESPRESSObin regarding the pre-installed
U-Boot, but we are able to replace it with a recent upstream version:
Follow the [instructions](http://wiki.macchiatobin.net/tiki-index.php?page=MACCHIATObin+Interface+list#Boot_Selection)
to switch the board to SD card booting, then flash the image on an empty
card and plug that into the board.

Note that the generated image is not yet directly usable for booting from the
eMMC.

### HiKey

The [LeMaker HiKey](http://www.lemaker.org/product-hikey-specification.html) is
supported with its 1 GB edition. The generated image can be used to boot from
SD card. This requires a recent version of the UEFI-based bootloader (tested
with version 1.4, September 17 2018).

You may also use the content of the boot and the root partition to fill the
corresponding partitions on the eMMC, but do no flash the complete image
directly to the eMMC because it does not contain any firmware.


Community Resources
-------------------

See [Jailhouse project](https://github.com/siemens/jailhouse).


License
-------

Unless otherwise stated in the respective file, files in this layer are
provided under the MIT license, see COPYING file. Patches (files ending with
.patch) are licensed according to their target project and file, typically
GPLv2.
