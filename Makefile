# makefile
# Tinashe Mukundi Chitamba
# 26 02 2020
JAVAC=/usr/bin/javac
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin/FlowSkeleton
docdir=./docs
.java.class:
	$(JAVAC) $<
$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<
all:
	javac -d bin $(SRCDIR)/*.java         
#CLASSES= surfaceLevel.class Terrain.class terrainWater.class flowThread.class \
#         FlowPanel.class Flow.class \

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class
run:   
	@java -cp bin Flow medsample_in.txt
docs:
	@javadoc -d $(docdir) $(SRCDIR)/*.java