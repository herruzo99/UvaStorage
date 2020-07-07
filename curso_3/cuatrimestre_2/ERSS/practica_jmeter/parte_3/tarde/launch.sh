../../jmeter/bin/jmeter.sh -n -t modificar_stock.jmx -l resultados40.jtl -j registro40.log -DTHREADS=40 -DRAMP_UP=13 -DDURATION=613 -DPERFMON="perfResultados40.jtl"
sleep 30
../../jmeter/bin/jmeter.sh -n -t modificar_stock.jmx -l resultados80.jtl -j registro80.log -DTHREADS=80 -DRAMP_UP=26 -DDURATION=626 -DPERFMON="perfResultados80.jtl"
sleep 30
../../jmeter/bin/jmeter.sh -n -t modificar_stock.jmx -l resultados160.jtl -j registro160.log -DTHREADS=160 -DRAMP_UP=52 -DDURATION=652 -DPERFMON="perfResultados160.jtl"
