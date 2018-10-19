# rgb-lcd-demo application
#
# This software is a part of ISAR.
# Copyright (C) 2018 ilbers GmbH

DESCRIPTION = "rgb-lcd-demo application for ISAR"

LICENSE = "gpl-2.0"
LIC_FILES_CHKSUM = "file://${LAYERDIR_isar}/licenses/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

PV = "0.1-1bf4876"

# NOTE: the following line duplicates the content in 'debian/control', but
#       for now it's the only way to correctly build bitbake pipeline.
#DEPENDS = "upm"

SRC_URI = "git://github.com/myo73/${PN}.git;protocol=https;destsuffix=${P}"
SRCREV = "1bf4876e9baf2bcc3ff070e49d363c68d6dc4328"

inherit dpkg
