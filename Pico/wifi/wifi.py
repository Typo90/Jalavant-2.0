import network
import utime as time
import usocket as socket
import random

# Set your wifi ssid and password here
WIFI_SSID = const('Jalavant')
WIFI_PASSWORD = const('Jalavant')

# These constants should match the client
BUF_SIZE = const(2)
SERVER_PORT = const(80)
TEST_ITERATIONS = const(10)

# Check if wifi details have been set
if len(WIFI_SSID) == 0 or len(WIFI_PASSWORD) == 0:
    raise RuntimeError('Please set wifi ssid and password in this script')

# Start connection
wlan = network.WLAN(network.AP_IF)
wlan.config(essid=WIFI_SSID, password=WIFI_PASSWORD)
wlan.active(True)
#wlan.connect(WIFI_SSID, WIFI_PASSWORD)

# Wait for connect success or failure
#max_wait = 20000000
'''while True:
    if wlan.status() < 0 or wlan.status() >= 3:
        break
    #max_wait -= 1
    print('waiting for connection...')
    time.sleep(1)

# Handle connection error
if wlan.status() != 3:
    raise RuntimeError('wifi connection failed %d' % wlan.status())
else:
    print('connected')
    status = wlan.ifconfig()
    print( 'ip = ' + status[0] )
'''

while wlan.active == False:
    pass
print("Access point active")
# Print out IP information
print(wlan.ifconfig())

# Open socket to the server
sock = socket.socket()
addr = ('0.0.0.0', SERVER_PORT)
sock.bind(addr)
sock.listen(1)
print('server listening on', addr)

# Wait for the client
#con = None
#con, addr = sock.accept()
#print('client connected from', addr)

#cur_buf = 0
# repeat test for a number of iterations
data = []
for i in range(288):
    data.append(i)
while True:
    # Read the data back from the client

    # Wait for the client
    print('waiting for connection')
    con = None
    con, addr = sock.accept()
    
    try:
        print('client connected from', addr)
        while True:
            data_client = con.recv(128)
            print('received "%s"' %data_client)
            if data_client:
                print('send data back to client')
                con.sendall(data_client)
            else:
                print('no more data')
    finally:
        con.close()
        sock.close()
        print("Completed")
    '''if(read_buf.decode == 1):
        print("Do something, like turn on LED")
        read_buf = None
        write_buf = bytearray(data)
        con.send(bytearray(write_buf))
        '''
    #print('read %d bytes from client' % len(read_buf))
    
'''for test_iteration in range(TEST_ITERATIONS):

    # Generate a buffer of random data
    random_list = []
    for n in range(BUF_SIZE):
        random_list.append(random.randint(0, 255))
    write_buf = bytearray(random_list)

    # write BUF_SIZE bytes to the client
    #write_len = con.send(bytearray(write_buf))
    #print('Written %d bytes to client' % write_len)

    # Check size of data written
    #if write_len != BUF_SIZ`E:
    #    raise RuntimeError('wrong amount of data written')

    # Read the data back from the client
    read_buf = con.read(BUF_SIZE)
    print(read_buf.decode())
    print('read %d bytes from client' % len(read_buf))

    # Check size of data received
    #if len(read_buf) != BUF_SIZE:
     #   raise RuntimeError('wrong amount of data read')

    # Check the data sent and received
    #if read_buf != write_buf:
     #   raise RuntimeError('buffer mismatch')
'''
# All done
con.close()
sock.close()
print("test completed")