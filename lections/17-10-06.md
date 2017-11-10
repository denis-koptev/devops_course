## Lection 6.10.17

# Virtulization

## Host components

	Physical host with its CPU, RAM, Storage and Network adapter
	Let's look at these components:

	CPU: 
		queueing, priority - to share capacity

	RAM:
		split into memory areas

	Storage:
		split into storage areas

	Network:
		packets from clients, virtual ip addresses

## Virtual machine

	vCPU, vRAM, vNik (adapter), (v)Storage
	In fact, Storages don't need virtualization

## Isolation

	We can't allow different VMs to break into each other's space

## Optimization

	Share resources optimally.

* In case of resource contention:

	CPU - queuing with priorities;

	RAM - dynamic memory provision;
		If there is no free memory: ask for it from host OS (priority will raise)
		(assign upon request)
		Second way: memory page sharing (if page didn't change, share it btw 2 os)

	Storage: dynamic disk enlargement (but it can be slow)
		Two disk types: thin/thick
		Compression, deduplication
		Question: if memory becomes free, how to turn it back to the host in case of thin disk?
		IO control (SIOC - VMWare)

	Network: 
		IO control (to avoid some kind of dos/ddos) (NIOC)

## Concepts

	I.e.: we reserved 2 gb to VM
	All memory (RAM): 8 gb. What can we do with 6 gb?
	File: vswp
	Storage: VMDK (file storage) (VMWare)

## Virtualization

* Variants of virtualization:

	Harware -> OS with Hypervisor (software virtualization or paravirtualization);
	There is emulator inside hypervisor in VirtualBox (not in VMWare);
	Machines interract with hardware with help of hypervisor and the OS

	Native virtualization: Hypervisor inside hardware;
	Machines get access to hardware directly

## Software Defined Datacenter

	Servers (+Management system) --> Storage (Fibre Channel, SAN)

	Many servers to one storage - is for security
	If server machine fails, all its data will stay on disk
	Management System (in VMWare - vCenter) will transfer responsibility to another server (must copy memory data, synchronize machines, kill one and turn on another) - virtual transfer of machines from one host to another.

## Software Defined Networks

	VMs connected to vSwitches (for isolation)
	Network card physically connected to 'trunk' to see all machines connected to different vSwitches

	trunk -> network card -> vSwitch -> port groups

	If there are a lot of hosts: it's not good to create vSwitch and port group for every machine
	Answer: dvSwitch, VMWare NSX. All configuration can be done virtually.